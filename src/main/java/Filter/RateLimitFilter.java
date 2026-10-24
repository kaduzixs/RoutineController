
package routine.controller.many.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final long MINUTO = 60_000L;

    // Limites
    private static final int LIMITE_GERAL = 5;
    private static final int LIMITE_LOGIN = 10;
    private static final int LIMITE_CADASTRO = 3;

    private static final long TEMPO_LOGIN = 15 * MINUTO;
    private static final long TEMPO_CADASTRO = 60 * MINUTO;

    private final ConcurrentHashMap<String, Bucket> buckets =
            new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String metodo = request.getMethod();
        String rota = request.getRequestURI();

        // LOG DE DIAGNÓSTICO
        System.out.println("FILTRO EXECUTADO: "
                + metodo + " " + rota
                + " | IP: " + request.getRemoteAddr());

        // Ignora requisições OPTIONS (CORS)
        if ("OPTIONS".equalsIgnoreCase(metodo)) {
            filterChain.doFilter(request, response);
            return;
        }

        String ip = request.getRemoteAddr();

        // 1. Limite geral: 5 requisições por minuto
        if (!permitir(ip + ":GERAL", LIMITE_GERAL, MINUTO)) {
            bloquear(response, "Limite geral de requisições excedido.");

            System.out.println("RATE LIMIT: BLOQUEADO (GERAL) | IP: " + ip);
            return;
        }

        // 2. Limite específico de login
        if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/login")) {

            if (!permitir(ip + ":LOGIN", LIMITE_LOGIN, TEMPO_LOGIN)) {
                bloquear(response, "Limite de tentativas de login excedido.");

                System.out.println("RATE LIMIT: BLOQUEADO (LOGIN) | IP: " + ip);
                return;
            }
        }

        // 3. Limite específico de cadastro
        if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/register")) {

            if (!permitir(ip + ":CADASTRO", LIMITE_CADASTRO, TEMPO_CADASTRO)) {
                bloquear(response, "Limite de cadastros excedido.");

                System.out.println("RATE LIMIT: BLOQUEADO (CADASTRO) | IP: " + ip);
                return;
            }
        }

        System.out.println("RATE LIMIT: PERMITIDO | IP: " + ip);

        filterChain.doFilter(request, response);
    }

    private boolean permitir(String chave, int limite, long intervalo) {
        Bucket bucket = buckets.computeIfAbsent(
                chave,
                k -> new Bucket(limite, intervalo)
        );

        return bucket.consumir();
    }

    private void bloquear(
            HttpServletResponse response,
            String mensagem
    ) throws IOException {

        response.setStatus(429);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Retry-After", "60");

        response.getWriter().write(
                "{\"erro\":\"" + mensagem + "\"}"
        );
    }

    private static class Bucket {

        private final int limite;
        private final long intervalo;

        private int requisicoes;
        private long inicio;

        public Bucket(int limite, long intervalo) {
            this.limite = limite;
            this.intervalo = intervalo;
            this.inicio = System.currentTimeMillis();
            this.requisicoes = 0;
        }

        public synchronized boolean consumir() {
            long agora = System.currentTimeMillis();

            // Reinicia a janela após o intervalo
            if (agora - inicio >= intervalo) {
                inicio = agora;
                requisicoes = 0;
            }

            if (requisicoes >= limite) {
                return false;
            }

            requisicoes++;
            return true;
        }
    }
}


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

    // Limites de requisicoes
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

        // Ignora preflight CORS
        if ("OPTIONS".equalsIgnoreCase(metodo)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Identificacao da requisicao
        String ip = request.getRemoteAddr();

        String tipo;
        String chave;
        int limite;
        long intervalo;
        int retryAfter;

        // Login
        if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/login")) {

            tipo = "LOGIN";
            chave = ip + ":LOGIN";
            limite = LIMITE_LOGIN;
            intervalo = TEMPO_LOGIN;
            retryAfter = 15 * 60;

        // Cadastro
        } else if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/register")) {

            tipo = "CADASTRO";
            chave = ip + ":CADASTRO";
            limite = LIMITE_CADASTRO;
            intervalo = TEMPO_CADASTRO;
            retryAfter = 60 * 60;

        // Demais requisicoes
        } else {
            tipo = "GERAL";
            chave = ip + ":GERAL";
            limite = LIMITE_GERAL;
            intervalo = MINUTO;
            retryAfter = 60;
        }

        // Log de entrada sem expor IP ou credenciais
        System.out.println(
                "RATE-FILTER | metodo=" + metodo
                + " | rota=" + rota
                + " | tipo=" + tipo
        );

        // Verifica limite
        Bucket bucket = buckets.computeIfAbsent(
                chave,
                k -> new Bucket()
        );

        boolean permitido = bucket.consumir(
                limite,
                intervalo
        );

        System.out.println(
                "RATE-DEBUG | tipo=" + tipo
                + " | permitido=" + permitido
                + " | limite=" + limite
        );

        // Bloqueia se exceder
        if (!permitido) {
            bloquear(
                    response,
                    "Limite de requisicoes excedido.",
                    retryAfter
            );
            return;
        }

        System.out.println(
                "RATE-FILTER | REQUISICAO LIBERADA"
        );

        filterChain.doFilter(request, response);
    }

    private void bloquear(
            HttpServletResponse response,
            String mensagem,
            int retryAfter
    ) throws IOException {

        System.out.println(
                "RATE-FILTER | BLOQUEIO 429"
        );

        response.setStatus(429);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.setHeader(
                "Retry-After",
                String.valueOf(retryAfter)
        );

        response.getWriter().write(
                "{\"erro\":\"" + mensagem + "\"}"
        );
    }

    private static class Bucket {

        private int requisicoes = 0;
        private long inicio = System.currentTimeMillis();

        public synchronized boolean consumir(
                int limite,
                long intervalo
        ) {

            long agora = System.currentTimeMillis();

            // Reinicia a janela
            if (agora - inicio >= intervalo) {
                inicio = agora;
                requisicoes = 0;
            }

            // Limite atingido
            if (requisicoes >= limite) {
                return false;
            }

            requisicoes++;

            return true;
        }
    }
}

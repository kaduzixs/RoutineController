
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

        // Log de diagnóstico
        System.out.println(
                "RATE-FILTER | metodo=" + metodo
                        + " | rota=" + rota
        );

        // Ignora preflight CORS
        if ("OPTIONS".equalsIgnoreCase(metodo)) {
            filterChain.doFilter(request, response);
            return;
        }

        String ip = request.getRemoteAddr();

        // LOGIN: 10 tentativas por 15 minutos
        if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/login")) {

            if (!permitir(
                    ip + ":LOGIN",
                    LIMITE_LOGIN,
                    TEMPO_LOGIN
            )) {
                bloquear(
                        response,
                        "Limite de tentativas de login excedido.",
                        15 * 60
                );
                return;
            }

        // CADASTRO: 3 tentativas por 60 minutos
        } else if ("POST".equalsIgnoreCase(metodo)
                && rota.equals("/api/user/register")) {

            if (!permitir(
                    ip + ":CADASTRO",
                    LIMITE_CADASTRO,
                    TEMPO_CADASTRO
            )) {
                bloquear(
                        response,
                        "Limite de cadastros excedido.",
                        60 * 60
                );
                return;
            }

        // DEMAIS ROTAS: 5 requisições por minuto
        } else {
            if (!permitir(
                    ip + ":GERAL",
                    LIMITE_GERAL,
                    MINUTO
            )) {
                bloquear(
                        response,
                        "Limite geral de requisicoes excedido.",
                        60
                );
                return;
            }
        }

        System.out.println(
                "RATE-FILTER | REQUISICAO LIBERADA"
        );

        filterChain.doFilter(request, response);
    }

    private boolean permitir(
            String chave,
            int limite,
            long intervalo
    ) {
        Bucket bucket = buckets.computeIfAbsent(
                chave,
                k -> new Bucket()
        );

        return bucket.consumir(limite, intervalo);
    }

    private void bloquear(
            HttpServletResponse response,
            String mensagem,
            int retryAfter
    ) throws IOException {

        System.out.println("RATE-FILTER | BLOQUEIO 429");

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

            if (requisicoes >= limite) {
                return false;
            }

            requisicoes++;
            return true;
        }
    }
}

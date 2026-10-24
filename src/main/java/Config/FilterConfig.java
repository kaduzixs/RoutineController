package Config;




import org.apache.catalina.filters.RateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {

        FilterRegistrationBean<RateLimitFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(new RateLimitFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registration;
    }
}


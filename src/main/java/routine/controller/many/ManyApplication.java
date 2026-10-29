
package routine.controller.many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

    @SpringBootApplication(
        scanBasePackages = {"routine.controller.many", "Controller", "Service"}
    )
    @EntityScan(
        basePackages = {"Repository", "Models"}
    )
    @EnableJpaRepositories(
        basePackages = {"Repository"}
    )
@EnableScheduling
public class ManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyApplication.class, args);
    }
}

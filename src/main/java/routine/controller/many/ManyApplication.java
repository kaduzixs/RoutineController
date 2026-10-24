
package routine.controller.many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"routine.controller.many",
		"Controller",
		"Service"
})
@EntityScan(basePackages = {"Repository", "Models"})
@EnableJpaRepositories(basePackages = {"Repository"})
public class ManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyApplication.class, args);
	}
}

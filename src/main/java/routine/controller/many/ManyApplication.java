package routine.controller.many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyApplication.class, args);
	}

}

package spring.error;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(proxyBeanMethods = false)
@ComponentScan("spring")
public class StartApplication {

	public static void main(String[] args) throws Exception {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(StartApplication.class, args);
	}
}

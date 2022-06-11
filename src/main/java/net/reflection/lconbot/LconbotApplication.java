package net.reflection.lconbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secret.properties")
public class LconbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(LconbotApplication.class, args);
	}

}

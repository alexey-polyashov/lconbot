package net.reflection.lconbot;

import net.reflection.lconbot.bot.TlgStateMachineApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secret.properties")
@EnableFeignClients
public class LconbotApplication {

	@Autowired
	private static TlgStateMachineApplicationListener test;

	public static void main(String[] args) {

		SpringApplication.run(LconbotApplication.class, args);

	}




}

package fr.isika.cdi07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.isika.cdi07.services", "fr.isika.cdi07.controllers"} )
@EntityScan(basePackages = ("fr.isika.cdi07.model"))
@EnableJpaRepositories("fr.isika.cdi07.dao")
public class demoApplication {
	public static void main(String[] args) {
		SpringApplication.run(demoApplication.class, args);
	}
}
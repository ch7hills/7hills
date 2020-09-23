package aia.cs.ms.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//Enable spring security java configuration.
@EnableWebSecurity
public class MsjwtSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsjwtSecurityApplication.class, args);
	}

}

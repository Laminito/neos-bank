package sn.neos

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NeosBankApplication {

	static void main(String[] args) {
		// Configuration de la propriété server.servlet.context-path
		System.setProperty("server.servlet.context-path", "/api/neos-bank");
		System.getProperties().put( "server.port", 9898 )
		SpringApplication.run(NeosBankApplication, args)
	}

}

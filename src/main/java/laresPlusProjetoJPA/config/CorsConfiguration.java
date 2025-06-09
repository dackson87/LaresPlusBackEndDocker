package laresPlusProjetoJPA.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfiguration implements WebMvcConfigures {
	
	@Override
	public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
}
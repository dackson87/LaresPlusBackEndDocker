package laresPlusProjetoJPA.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface WebMvcConfigures {

	void addCorsMappings(CorsRegistry registry);

}

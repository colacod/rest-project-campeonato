package br.com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import br.com.project.constantes.ApplicationConstantes;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setDefaultEncoding(ApplicationConstantes.UTF_8.name());
		source.setBasenames("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}

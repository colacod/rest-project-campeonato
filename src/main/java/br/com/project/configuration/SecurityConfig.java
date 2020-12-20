package br.com.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.project.component.JwtFilterComponent;
import br.com.project.enums.UrlFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilterComponent jwtFilter;

	@Bean
	public FilterRegistrationBean<JwtFilterComponent> securityFilterChainRegistration() {
		FilterRegistrationBean<JwtFilterComponent> registrationBean = new FilterRegistrationBean<JwtFilterComponent>(
				jwtFilter);
		registrationBean.addUrlPatterns(UrlFilter.getValues());
		return registrationBean;
	}

}

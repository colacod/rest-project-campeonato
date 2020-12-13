package br.com.project.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class PropertyConfig {

	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public static Gson gson() {
		return new GsonBuilder().create();
	}

	public static RestTemplate restTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

}

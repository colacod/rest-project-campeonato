package br.com.project.service;

import br.com.project.resource.Time;

public interface JwtService {

	String getToken(Time user);

	Time getUser(String token);
}

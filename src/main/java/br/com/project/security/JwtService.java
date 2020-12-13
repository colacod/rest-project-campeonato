package br.com.project.security;

import br.com.project.resource.Time;
import br.com.project.resource.User;

public interface JwtService {

	String getToken(Time user);

	User getUser(String token);
}

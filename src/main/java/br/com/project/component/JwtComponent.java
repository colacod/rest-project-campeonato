package br.com.project.component;

import br.com.project.resource.Time;

public interface JwtComponent {

	String getToken(Time user);

	Time getUser(String token);
}

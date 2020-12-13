package br.com.project.exception;

import org.springframework.hateoas.Link;

import lombok.Getter;

@Getter
public class RegisterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Object logRef;
	private final Link link;

	public RegisterNotFoundException(Object logRef, String message, Link link) {
		super(message);
		this.logRef = logRef;
		this.link = link;
	}
}

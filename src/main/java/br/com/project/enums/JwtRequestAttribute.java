package br.com.project.enums;

import lombok.Getter;

public enum JwtRequestAttribute {

	USER("userJwt");

	@Getter
	private String code;

	private JwtRequestAttribute(String code) {
		this.code = code;
	}
}

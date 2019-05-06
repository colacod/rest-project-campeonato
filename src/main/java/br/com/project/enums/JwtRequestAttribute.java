package br.com.project.enums;

public enum JwtRequestAttribute {

	USER("userJwt");

	private String code;

	private JwtRequestAttribute(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

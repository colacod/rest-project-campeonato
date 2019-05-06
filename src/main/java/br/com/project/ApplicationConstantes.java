package br.com.project;

import java.nio.charset.Charset;

public class ApplicationConstantes {

	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Integer INTEGER_ZERO = 0;

	private ApplicationConstantes() {
		throw new IllegalStateException("Classe utilitaria");
	}
}
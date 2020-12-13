package br.com.project.constantes;

import java.nio.charset.Charset;

public class ApplicationConstantes {

	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Integer INTEGER_ZERO = 0;
	public static final Integer INTEGER_UM = 1;
	public static final Integer INTEGER_DOIS = 2;

	public static final String LOG_REGISTER_NOT_FOUND_EXCEPTION = "Register not found.";
	public static final String LOG_RECORD_ALREADY_EXISTS_EXCEPTION = "Record already exists.";

	public static final String AUTHORIZATION = "Authorization";
	public static final String BASE_PATH = "/v1";

	public static final String CAMPEONATO_PATH = BASE_PATH + "/campeonato";
	public static final String GRUPO_PATH = BASE_PATH + "/grupo";
	public static final String GRUPO_VINCULO_PATH = BASE_PATH + "/grupovinculo";
	public static final String PLAYOFF_PATH = BASE_PATH + "/playoff";
	public static final String RESULTADO_PATH = BASE_PATH + "/resultado";
	public static final String TIME_PATH = BASE_PATH + "/time";

	public static final String GET_PATH = "/get";
	public static final String SAVE_PATH = "/save";
	public static final String UPDATE_PATH = "/update";
	public static final String DELETE_PATH = "/delete";

	private ApplicationConstantes() {
		throw new IllegalStateException("Classe utilitaria");
	}
}

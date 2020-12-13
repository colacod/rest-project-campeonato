package br.com.project.enums;

import java.util.Arrays;

import lombok.Getter;

public enum UrlFilter {

	URL_ROOT("/v3/*");

	@Getter
	private String url;

	private UrlFilter(String url) {
		this.url = url;
	}

	public static String[] getValues() {
		return Arrays.stream(values()).map(v -> v.getUrl()).toArray(String[]::new);
	}
}

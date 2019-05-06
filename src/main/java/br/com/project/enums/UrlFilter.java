package br.com.project.enums;

import java.util.ArrayList;
import java.util.List;

public enum UrlFilter {

	URL_ROOT("/v1/*");

	private String url;

	private UrlFilter(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public static String[] getValues() {
		List<String> listUrl = new ArrayList<>();
		for (UrlFilter url : values()) {
			listUrl.add(url.getUrl());
		}
		return listUrl.stream().toArray(String[]::new);
	}
}

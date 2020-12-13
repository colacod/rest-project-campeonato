package br.com.project.security.impl;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.project.resource.Time;
import br.com.project.resource.User;
import br.com.project.security.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.expire.hours}")
	private String expireHours;

	@Value("${jwt.token.secret}")
	private String plainSecret;

	private String encodedSecret;

	@PostConstruct
	protected void init() {
		this.encodedSecret = generateEncodedSecret(this.plainSecret);
	}

	@Override
	public String getToken(Time user) {
		return getToken(user, this.encodedSecret);
	}

	@Override
	public User getUser(String token) {
		return getUser(token, this.encodedSecret);
	}

	private String getToken(Time user, String encodedSecret) {
		return null; // TODO
	}

	private User getUser(String token, String encodedSecret) {
		return null; // TODO
	}

	private String generateEncodedSecret(String plainSecret) {
		if (StringUtils.isEmpty(plainSecret)) {
			throw new IllegalArgumentException("JWT secret cannot be null or empty.");
		}
		return Base64.getEncoder().encodeToString(this.plainSecret.getBytes());
	}

	private Date getExpirationTime() {
		Date now = new Date();
		Long expireInMilis = TimeUnit.HOURS.toMillis(Long.parseLong(expireHours));
		return new Date(expireInMilis + now.getTime());
	}
}

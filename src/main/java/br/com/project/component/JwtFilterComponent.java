package br.com.project.component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import br.com.project.enums.JwtRequestAttribute;
import br.com.project.resource.Time;
import br.com.project.service.JwtService;
import io.jsonwebtoken.JwtException;

@Component
public class JwtFilterComponent implements Filter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final Integer BEARER_LENGTH = "Bearer".length();

	@Autowired
	private JwtService jwtTokenService;

	@Value("${jwt.auth.header}")
	private String authHeader;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("JWT filtro iniciado.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		final String authHeaderVal = httpRequest.getHeader(authHeader);

		if (authHeaderVal == null) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		try {
			String token = authHeaderVal.substring(BEARER_LENGTH, authHeaderVal.length());
			Time user = jwtTokenService.getUser(token);
			httpRequest.setAttribute(JwtRequestAttribute.USER.getCode(), user);
			chain.doFilter(httpRequest, httpResponse);
		} catch (JwtException e) {
			log.error("Acesso não permitido", e);
			httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
	}

	@Override
	public void destroy() {
		log.info("JWT filtro destruido");
	}
}

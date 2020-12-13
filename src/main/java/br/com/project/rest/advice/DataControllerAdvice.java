package br.com.project.rest.advice;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.project.rest.routing.MethodArgumentNotValidExceptionHandler;
import br.com.project.rest.routing.RecursoExceptionHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class DataControllerAdvice implements RecursoExceptionHandler, MethodArgumentNotValidExceptionHandler {

	@Override
	public void logException(Exception e) {
		log.error(e.getMessage());
		log.error(Arrays.toString(e.getStackTrace()));
	}

	@Override
	public void logThrowable(Throwable e) {
		log.error(e.getMessage());
		log.error(Arrays.toString(e.getStackTrace()));
	}
}

package br.com.project.rest.routing;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.VndErrors;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface MethodArgumentNotValidExceptionHandler extends ErrorBuild {

	@ExceptionHandler
	default ResponseEntity<VndErrors> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		List<VndError> vndErrors = e.getBindingResult().getFieldErrors().stream()
				.map(error -> new VndError(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
		return error(e, HttpStatus.BAD_REQUEST, vndErrors);
	}

}

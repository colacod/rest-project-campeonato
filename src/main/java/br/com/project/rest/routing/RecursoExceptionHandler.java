package br.com.project.rest.routing;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;

public interface RecursoExceptionHandler extends ErrorBuild {

	@ExceptionHandler
	default ResponseEntity<VndErrors> RegisterNotFoundExceptionHandler(RegisterNotFoundException e) {
		return error(e, HttpStatus.NOT_FOUND, new Gson().toJson(e.getLogRef()), e.getMessage(), e.getLink());
	}

	@ExceptionHandler
	default ResponseEntity<VndErrors> RecordAlreadyExistsExceptionHandler(RecordAlreadyExistsException e) {
		return error(e, HttpStatus.BAD_REQUEST, new Gson().toJson(e.getLogRef()), e.getMessage(), e.getLink());
	}

}

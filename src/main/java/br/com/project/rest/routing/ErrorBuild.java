package br.com.project.rest.routing;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.VndErrors;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ErrorBuild {

	default ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef,
			final String message, final Link link) {

		logException(exception);

		return new ResponseEntity<>(
				new VndErrors(logRef, Optional.of(message).orElse(exception.getClass().getSimpleName()), link),
				httpStatus);
	}

	default ResponseEntity<VndErrors> error(final Throwable throwable, final HttpStatus httpStatus, final String logRef,
			final String message, final Link link) {

		logThrowable(throwable);

		return new ResponseEntity<>(new VndErrors(logRef, message, link), httpStatus);
	}

	default ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus,
			final List<VndError> vndErrors) {

		logException(exception);

		return new ResponseEntity<>(new VndErrors(vndErrors), httpStatus);
	}

	default ResponseEntity<VndErrors> error(final Throwable throwable, final HttpStatus httpStatus,
			final List<VndError> vndErrors) {

		logThrowable(throwable);

		return new ResponseEntity<>(new VndErrors(vndErrors), httpStatus);
	}

	void logException(Exception e);

	void logThrowable(Throwable e);

}

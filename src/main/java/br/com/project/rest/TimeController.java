package br.com.project.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.constantes.ApplicationConstantes;
import br.com.project.entity.TimeEntity;
import br.com.project.resource.Time;
import br.com.project.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = ApplicationConstantes.TIME_PATH)
@Api(value = "API Time")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class TimeController {

	@Autowired
	private TimeService timeService;

	@ApiOperation(value = "Search a time", notes = "Search a time", nickname = "getTimeUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Time found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<TimeEntity>> get(@RequestBody Time time, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(
				timeService.get(time, page, linkTo(methodOn(TimeController.class).get(time, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a time", notes = "Save a time", nickname = "saveTimeUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Time added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Time> save(@Valid @RequestBody Time time) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(timeService.save(time, linkTo(methodOn(TimeController.class).save(time)).withSelfRel()));
	}

	@ApiOperation(value = "Update a time", notes = "Update a time", nickname = "updateTimeUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Time updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Time> update(@Valid @RequestBody Time time) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(timeService.update(time, linkTo(methodOn(TimeController.class).update(time)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a time", notes = "Delete a time", nickname = "deleteTimeUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Time successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody Time time) {
		return ResponseEntity.status(HttpStatus.OK).body(timeService.delete(time.getIdTime(),
				linkTo(methodOn(TimeController.class).delete(time)).withSelfRel()));
	}

}

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
import br.com.project.entity.PlayoffEntity;
import br.com.project.resource.Playoff;
import br.com.project.service.PlayoffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ApplicationConstantes.PLAYOFF_PATH)
@Api(value = "API Playoff")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class PlayoffController {

	@Autowired
	private PlayoffService playoffService;

	@ApiOperation(value = "Search a playoff", notes = "Search a playoff", nickname = "getPlayoffUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Playoff found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<PlayoffEntity>> get(@RequestBody Playoff playoff, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(playoffService.get(playoff, page,
				linkTo(methodOn(PlayoffController.class).get(playoff, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a playoff", notes = "Save a playoff", nickname = "savePlayoffUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Playoff added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Playoff> save(@Valid @RequestBody Playoff playoff) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				playoffService.save(playoff, linkTo(methodOn(PlayoffController.class).save(playoff)).withSelfRel()));
	}

	@ApiOperation(value = "Update a playoff", notes = "Update a playoff", nickname = "updatePlayoffUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Playoff updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Playoff> update(@Valid @RequestBody Playoff playoff) {
		return ResponseEntity.status(HttpStatus.OK).body(playoffService.update(playoff,
				linkTo(methodOn(PlayoffController.class).update(playoff)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a playoff", notes = "Delete a playoff", nickname = "deletePlayoffUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Playoff successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody Playoff playoff) {
		return ResponseEntity.status(HttpStatus.OK).body(playoffService.delete(playoff.getIdPlayoff(),
				linkTo(methodOn(PlayoffController.class).delete(playoff)).withSelfRel()));
	}

}

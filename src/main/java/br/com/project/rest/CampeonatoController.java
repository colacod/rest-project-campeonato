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
import br.com.project.entity.CampeonatoEntity;
import br.com.project.resource.Campeonato;
import br.com.project.service.CampeonatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = ApplicationConstantes.CAMPEONATO_PATH)
@Api(value = "API Campeonato")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;

	@ApiOperation(value = "Search a campeonato", notes = "Search a campeonato", nickname = "getCampeonatoUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Campeonato found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CampeonatoEntity>> get(@RequestBody Campeonato campeonato, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.get(campeonato, page,
				linkTo(methodOn(CampeonatoController.class).get(campeonato, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a campeonato", notes = "Save a campeonato", nickname = "saveCampeonatoUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Campeonato added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campeonato> save(@Valid @RequestBody Campeonato campeonato) {
		return ResponseEntity.status(HttpStatus.CREATED).body(campeonatoService.save(campeonato,
				linkTo(methodOn(CampeonatoController.class).save(campeonato)).withSelfRel()));
	}

	@ApiOperation(value = "Update a campeonato", notes = "Update a campeonato", nickname = "updateCampeonatoUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Campeonato updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campeonato> update(@Valid @RequestBody Campeonato campeonato) {
		return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.update(campeonato,
				linkTo(methodOn(CampeonatoController.class).update(campeonato)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a campeonato", notes = "Delete a campeonato", nickname = "deleteCampeonatoUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Campeonato successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody Campeonato campeonato) {
		return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.delete(campeonato.getIdCampeonato(),
				linkTo(methodOn(CampeonatoController.class).delete(campeonato)).withSelfRel()));
	}

}

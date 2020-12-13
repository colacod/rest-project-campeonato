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
import br.com.project.entity.ResultadoEntity;
import br.com.project.resource.Resultado;
import br.com.project.service.ResultadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ApplicationConstantes.RESULTADO_PATH)
@Api(value = "API Resultado")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@ApiOperation(value = "Search a resultado", notes = "Search a resultado", nickname = "getResultadoSUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Resultado found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ResultadoEntity>> get(@RequestBody Resultado resultado, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(resultadoService.get(resultado, page,
				linkTo(methodOn(ResultadoController.class).get(resultado, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a resultado", notes = "Save a resultado", nickname = "saveResultadoUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Resultado added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resultado> save(@Valid @RequestBody Resultado resultado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(resultadoService.save(resultado,
				linkTo(methodOn(ResultadoController.class).save(resultado)).withSelfRel()));
	}

	@ApiOperation(value = "Update a resultado", notes = "Update a resultado", nickname = "updateResultadoUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Resultado updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resultado> update(@Valid @RequestBody Resultado resultado) {
		return ResponseEntity.status(HttpStatus.OK).body(resultadoService.update(resultado,
				linkTo(methodOn(ResultadoController.class).update(resultado)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a resultado", notes = "Delete a resultado", nickname = "deleteResultadoUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Resultado successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody Resultado resultado) {
		return ResponseEntity.status(HttpStatus.OK).body(resultadoService.delete(resultado.getIdResultado(),
				linkTo(methodOn(ResultadoController.class).delete(resultado)).withSelfRel()));
	}

}

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
import br.com.project.entity.GrupoVinculoEntity;
import br.com.project.resource.GrupoVinculo;
import br.com.project.service.GrupoVinculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ApplicationConstantes.GRUPO_VINCULO_PATH)
@Api(value = "API GrupoVinculo")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class GrupoVinculoController {

	@Autowired
	private GrupoVinculoService grupoVinculoService;

	@ApiOperation(value = "Search a grupo vinculo", notes = "Search a grupo vinculo", nickname = "getGrupoVinculoUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo vinculo found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<GrupoVinculoEntity>> get(@RequestBody GrupoVinculo grupoVinculo, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(grupoVinculoService.get(grupoVinculo, page,
				linkTo(methodOn(GrupoVinculoController.class).get(grupoVinculo, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a grupo vinculo", notes = "Save a grupo vinculo", nickname = "saveGrupoVinculoUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Grupo vinculo added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GrupoVinculo> save(@Valid @RequestBody GrupoVinculo grupoVinculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoVinculoService.save(grupoVinculo,
				linkTo(methodOn(GrupoVinculoController.class).save(grupoVinculo)).withSelfRel()));
	}

	@ApiOperation(value = "Update a grupo vinculo", notes = "Update a grupo vinculo", nickname = "updateGrupoVinculoUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo vinculo updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GrupoVinculo> update(@Valid @RequestBody GrupoVinculo grupoVinculo) {
		return ResponseEntity.status(HttpStatus.OK).body(grupoVinculoService.update(grupoVinculo,
				linkTo(methodOn(GrupoVinculoController.class).update(grupoVinculo)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a grupo vinculo", notes = "Delete a grupo vinculo", nickname = "deleteGrupoVinculoUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo vinculo successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody GrupoVinculo grupoVinculo) {
		return ResponseEntity.status(HttpStatus.OK).body(grupoVinculoService.delete(grupoVinculo.getIdGrupoVinculo(),
				linkTo(methodOn(GrupoVinculoController.class).delete(grupoVinculo)).withSelfRel()));
	}

}

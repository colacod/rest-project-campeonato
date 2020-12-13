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
import br.com.project.entity.GrupoEntity;
import br.com.project.resource.Grupo;
import br.com.project.service.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ApplicationConstantes.GRUPO_PATH)
@Api(value = "API Grupo")
@ApiResponses({ @ApiResponse(code = 400, message = "Bad Request."),
		@ApiResponse(code = 500, message = "Unexpected error.") })
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@ApiOperation(value = "Search a grupo", notes = "Search a grupo", nickname = "getGrupoUsingPOST")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo found."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.GET_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<GrupoEntity>> get(@RequestBody Grupo grupo, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK).body(
				grupoService.get(grupo, page, linkTo(methodOn(GrupoController.class).get(grupo, page)).withSelfRel()));
	}

	@ApiOperation(value = "Save a grupo", notes = "Save a grupo", nickname = "saveGrupoUsingPUT")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({ @ApiResponse(code = 201, message = "Grupo added successfully."),
			@ApiResponse(code = 400, message = "Record already exists.") })
	@RequestMapping(path = ApplicationConstantes.SAVE_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grupo> save(@Valid @RequestBody Grupo grupo) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(grupoService.save(grupo, linkTo(methodOn(GrupoController.class).save(grupo)).withSelfRel()));
	}

	@ApiOperation(value = "Update a grupo", notes = "Update a grupo", nickname = "updateGrupoUsingPATCH")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo updated successfully."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.UPDATE_PATH, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grupo> update(@Valid @RequestBody Grupo grupo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(grupoService.update(grupo, linkTo(methodOn(GrupoController.class).update(grupo)).withSelfRel()));
	}

	@ApiOperation(value = "Delete a grupo", notes = "Delete a grupo", nickname = "deleteGrupoUsingDELETE")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses({ @ApiResponse(code = 200, message = "Grupo successfully deleted."),
			@ApiResponse(code = 404, message = "Resource not found.") })
	@RequestMapping(path = ApplicationConstantes.DELETE_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@Valid @RequestBody Grupo grupo) {
		return ResponseEntity.status(HttpStatus.OK).body(grupoService.delete(grupo.getIdGrupo(),
				linkTo(methodOn(GrupoController.class).delete(grupo)).withSelfRel()));
	}

}

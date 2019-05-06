package br.com.project.rest;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.resource.Grupo;
import br.com.project.resource.GrupoVinculo;
import br.com.project.service.GrupoService;
import br.com.project.service.GrupoVinculoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoVinculoService grupoVinculoService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grupo> getGrupo(@PathVariable(value = "id") Integer id) {
		Grupo grupo = grupoService.getGrupo(id);
		if (Objects.isNull(grupo)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(grupo);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Grupo>> getGrupos() {
		List<Grupo> grupos = grupoService.getGrupos();
		if (grupos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(grupos);
	}

	@RequestMapping(path = "/vinculo/get/{idGrupo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GrupoVinculo>> getGruposVinculado(@PathVariable(value = "idGrupo") Integer idGrupo) {
		List<GrupoVinculo> grupos = grupoVinculoService.getGrupo(idGrupo);
		if (grupos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(grupos);
	}

	@RequestMapping(path = "/vinculo/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GrupoVinculo>> getTodosGruposVinculado() {
		List<GrupoVinculo> grupos = grupoVinculoService.getGrupos();
		if (grupos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(grupos);
	}

	@RequestMapping(path = "/set", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grupo> setGrupo(@Valid @RequestBody Grupo grupo) {
		Grupo resultGrupo = grupoService.setGrupo(grupo);
		if (Objects.isNull(resultGrupo)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultGrupo);
	}
}

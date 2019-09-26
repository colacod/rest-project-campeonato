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

import br.com.project.resource.Campeonato;
import br.com.project.service.CampeonatoModuleService;
import br.com.project.service.CampeonatoService;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

	@Autowired
	private CampeonatoService campeonatoService;

	@Autowired
	private CampeonatoModuleService campeonatoModuleService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campeonato> getCampeonato(@PathVariable(value = "id") Integer id) {
		Campeonato campeonato = campeonatoService.getCampeonato(id);
		if (Objects.isNull(campeonato)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(campeonato);
	}

	@RequestMapping(path = "/set", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campeonato> setCampeonato(@Valid @RequestBody Campeonato campeonato) {
		Campeonato resultCampeonato = campeonatoService.saveCampeonato(campeonato);
		if (Objects.isNull(resultCampeonato)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultCampeonato);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Campeonato>> getCampeonatos() {
		List<Campeonato> campeonatos = campeonatoService.getCampeonatos();
		if (Objects.isNull(campeonatos)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(campeonatos);
	}

	@RequestMapping(path = "/criar/grupo/{idCampeonato}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> criarCampeonatoGrupo(@PathVariable(value = "idCampeonato") Integer idCampeonato) {
		campeonatoModuleService.criarGrupoCampeonato(idCampeonato);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(path = "/criar/playoff/campeonato/{idCampeonato}/proximogrupo/{idProximoGrupo}/limite/{limite}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> criarCampeonatoPlayoff(@PathVariable(value = "idCampeonato") Integer idCampeonato,
			@PathVariable(value = "idProximoGrupo") Integer idProximoGrupo,
			@PathVariable(value = "limite") Integer limiteTimePorGrupo) {
		campeonatoModuleService.criarPlayoffCampeonato(idCampeonato, idProximoGrupo, limiteTimePorGrupo);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(path = "/criar/sequence/campeonato/{idCampeonato}/grupo/{idGrupo}/proximogrupo/{idProximoGrupo}/limite/{limite}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> criarCampeonatoSequence(@PathVariable(value = "idCampeonato") Integer idCampeonato,
			@PathVariable(value = "idGrupo") Integer idGrupo,
			@PathVariable(value = "idProximoGrupo") Integer idProximoGrupo,
			@PathVariable(value = "limite") Integer limiteTimePorGrupo) {
		campeonatoModuleService.criarSequencePlayoffCampeonato(idCampeonato, idGrupo, idProximoGrupo,
				limiteTimePorGrupo);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}

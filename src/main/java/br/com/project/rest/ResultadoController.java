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

import br.com.project.resource.Resultado;
import br.com.project.service.ResultadoService;

@RestController
@RequestMapping("/resultado")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@RequestMapping(path = "/get/campeonato/{idCampeonato}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Resultado>> getResultado(@PathVariable(value = "idCampeonato") Integer idCampeonato) {
		List<Resultado> resultados = resultadoService.getResultado(idCampeonato);
		if (resultados.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultados);
	}

	@RequestMapping(path = "/get/campeonato/{idCampeonato}/grupo/{idGrupo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Resultado>> getResultadoPorGrupoCampeonato(
			@PathVariable(value = "idCampeonato") Integer idCampeonato,
			@PathVariable(value = "idGrupo") Integer idGrupo) {
		List<Resultado> resultados = resultadoService.getResultadoPorGrupoCampeonato(idCampeonato, idGrupo);
		if (resultados.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultados);
	}

	@RequestMapping(path = "/set", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resultado> setResultados(@Valid @RequestBody Resultado resultado) {
		Resultado result = resultadoService.setResultado(resultado);
		if (Objects.isNull(result)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}

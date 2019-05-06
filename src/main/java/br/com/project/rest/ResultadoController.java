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

import br.com.project.component.ResultadoComponent;
import br.com.project.resource.Resultado;

@RestController
@RequestMapping("/resultado")
public class ResultadoController {

	@Autowired
	private ResultadoComponent resultadoComponent;

	@RequestMapping(path = "/get/{idCampeonato}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Resultado>> getResultados(@PathVariable(value = "idCampeonato") Integer idCampeonato) {
		List<Resultado> resultados = resultadoComponent.getResultado(idCampeonato);
		if (resultados.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(resultados);
	}

	@RequestMapping(path = "/set", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resultado> setResultados(@Valid @RequestBody Resultado resultado) {
		Resultado result = resultadoComponent.setResultado(resultado);
		if (Objects.isNull(result)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}
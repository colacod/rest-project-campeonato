package br.com.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.component.PlayoffComponent;
import br.com.project.resource.Playoff;

@RestController
@RequestMapping("/playoff")
public class PlayoffController {

	@Autowired
	private PlayoffComponent playoffComponent;

	@RequestMapping(path = "/get/{idCampeonato}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Playoff>> getPlayoff(@PathVariable(value = "idCampeonato") Integer idCampeonato) {
		List<Playoff> playoffs = playoffComponent.getPlayoff(idCampeonato);
		if (playoffs.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(playoffs);
	}
}

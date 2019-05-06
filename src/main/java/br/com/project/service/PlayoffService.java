package br.com.project.service;

import java.util.List;

import br.com.project.resource.Playoff;

public interface PlayoffService {

	List<Playoff> getPlayoff(Integer idCampeonato);

	Playoff setPlayoff(Playoff playoff);

}

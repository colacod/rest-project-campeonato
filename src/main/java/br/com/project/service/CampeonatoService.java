package br.com.project.service;

import java.util.List;

import br.com.project.resource.Campeonato;

public interface CampeonatoService {

	Campeonato getCampeonato(Integer id);

	Campeonato saveCampeonato(Campeonato campeonato);

	Campeonato addTotalCampeonato(Integer idCampeonato);

	List<Campeonato> getCampeonatos();

}

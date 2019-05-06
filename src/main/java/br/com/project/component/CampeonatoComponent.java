package br.com.project.component;

import java.util.List;

import br.com.project.resource.Campeonato;

public interface CampeonatoComponent {

	Campeonato getCampeonato(Integer id);

	Campeonato saveCampeonato(Campeonato campeonato);

	Campeonato addTotalCampeonato(Integer idCampeonato);

	List<Campeonato> getCampeonatos();

}

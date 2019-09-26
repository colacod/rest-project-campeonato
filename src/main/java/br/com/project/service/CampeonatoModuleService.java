package br.com.project.service;

public interface CampeonatoModuleService {

	void criarGrupoCampeonato(Integer idCampeonato);

	void criarPlayoffCampeonato(Integer idCampeonato, Integer idProximoGrupo, Integer limiteTimePorGrupo);

	void criarSequencePlayoffCampeonato(Integer idCampeonato, Integer idGrupo, Integer idProximoGrupo,
			Integer limiteTimePorGrupo);

}

package br.com.project.service;

public interface CampeonatoModuleService {

	void criarGrupoCampeonato(Integer idCampeonato);

	void criarPlayoffCampeonato(Integer idCampeonato, Integer idProximoGrupo);

	void criarSequencePlayoffCampeonato(Integer idCampeonato, Integer idGrupo, Integer idProximoGrupo);

}

package br.com.project.service;

public interface CampeonatoService {

	void criarGrupoCampeonato(Integer idCampeonato);

	void criarPlayoffCampeonato(Integer idCampeonato, Integer idGrupo, Integer limiteDeTimes);

}

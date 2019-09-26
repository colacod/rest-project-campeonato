package br.com.project.service;

import java.util.List;

import br.com.project.resource.Resultado;

public interface ResultadoService {

	List<Resultado> getResultado(Integer idCampeonato);

	Resultado setResultado(Resultado resultado);

	List<Resultado> getResultadoPorGrupo(Integer idGrupo);

	List<Resultado> getResultadoPorGrupoCampeonato(Integer idCampeonato, Integer idGrupo);

}

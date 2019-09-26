package br.com.project.service;

import java.util.List;

import br.com.project.resource.GrupoVinculo;
import br.com.project.resource.Time;

public interface GrupoVinculoService {

	List<GrupoVinculo> getGrupo(Integer idGrupo, Integer idCampeonato);

	GrupoVinculo setGrupo(Integer idGrupo, Integer idTime, Integer idCampeonato);

	List<GrupoVinculo> getGrupos();

	List<Time> getTimesGrupos(Integer idCampeonato);

}

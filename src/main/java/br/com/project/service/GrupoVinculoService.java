package br.com.project.service;

import java.util.List;

import br.com.project.resource.GrupoVinculo;

public interface GrupoVinculoService {

	List<GrupoVinculo> getGrupo(Integer idGrupo);

	GrupoVinculo setGrupo(Integer idGrupo, Integer idTime);

	List<GrupoVinculo> getGrupos();

}

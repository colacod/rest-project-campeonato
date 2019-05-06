package br.com.project.component;

import java.util.List;

import br.com.project.resource.GrupoVinculo;

public interface GrupoVinculoComponent {

	List<GrupoVinculo> getGrupo(Integer idGrupo);

	GrupoVinculo setGrupo(Integer idGrupo, Integer idTime);

	List<GrupoVinculo> getGrupos();

}

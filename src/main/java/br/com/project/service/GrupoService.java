package br.com.project.service;

import java.util.List;

import br.com.project.resource.Grupo;

public interface GrupoService {

	Grupo getGrupo(Integer id);

	Grupo setGrupo(Grupo grupo);

	List<Grupo> buscarTodosGrupos();

	List<Grupo> getGrupos();

	List<Grupo> buscarTodosPlayoff();

}

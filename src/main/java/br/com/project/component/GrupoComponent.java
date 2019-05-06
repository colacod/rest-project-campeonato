package br.com.project.component;

import java.util.List;

import br.com.project.resource.Grupo;

public interface GrupoComponent {

	Grupo getGrupo(Integer id);

	Grupo setGrupo(Grupo grupo);

	List<Grupo> buscarTodosGrupos();

	List<Grupo> buscarTodosGruposQuartas();

	List<Grupo> buscarTodosGruposSemis();

	List<Grupo> buscarTodosGruposFinais();

	List<Grupo> getGrupos();

}

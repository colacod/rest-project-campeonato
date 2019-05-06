package br.com.project.component;

import java.util.List;

import br.com.project.resource.Resultado;

public interface ResultadoComponent {

	List<Resultado> getResultado(Integer idCampeonato);

	Resultado setResultado(Resultado resultado);

	List<Resultado> getResultadoPorGrupo(Integer grupo);

}

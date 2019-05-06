package br.com.project.component;

import java.util.List;

import br.com.project.resource.Playoff;

public interface PlayoffComponent {

	List<Playoff> getPlayoff(Integer idCampeonato);

	Playoff setPlayoff(Playoff playoff);

}

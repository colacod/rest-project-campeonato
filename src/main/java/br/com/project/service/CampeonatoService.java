package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.CampeonatoEntity;
import br.com.project.resource.Campeonato;

public interface CampeonatoService {

	Page<CampeonatoEntity> get(Campeonato campeonato, Pageable page, Link link);

	Campeonato save(Campeonato campeonato, Link link);

	Campeonato update(Campeonato campeonato, Link link);

	Boolean delete(Long id, Link link);

}

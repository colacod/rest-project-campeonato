package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.GrupoEntity;
import br.com.project.resource.Grupo;

public interface GrupoService {

	Page<GrupoEntity> get(Grupo grupo, Pageable page, Link link);

	Grupo save(Grupo grupo, Link link);

	Grupo update(Grupo grupo, Link link);

	Boolean delete(Long id, Link link);

}

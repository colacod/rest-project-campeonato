package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.GrupoVinculoEntity;
import br.com.project.resource.GrupoVinculo;

public interface GrupoVinculoService {

	Page<GrupoVinculoEntity> get(GrupoVinculo grupoVinculo, Pageable page, Link link);

	GrupoVinculo save(GrupoVinculo grupoVinculo, Link link);

	GrupoVinculo update(GrupoVinculo grupoVinculo, Link link);

	Boolean delete(Long id, Link link);

}

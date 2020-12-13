package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.PlayoffEntity;
import br.com.project.resource.Playoff;

public interface PlayoffService {

	Page<PlayoffEntity> get(Playoff playoff, Pageable page, Link link);

	Playoff save(Playoff playoff, Link link);

	Playoff update(Playoff playoff, Link link);

	Boolean delete(Long id, Link link);

}

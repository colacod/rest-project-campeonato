package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.ResultadoEntity;
import br.com.project.resource.Resultado;

public interface ResultadoService {

	Page<ResultadoEntity> get(Resultado resultado, Pageable page, Link link);

	Resultado save(Resultado resultado, Link link);

	Resultado update(Resultado resultado, Link link);

	Boolean delete(Long id, Link link);

}

package br.com.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.project.entity.TimeEntity;
import br.com.project.resource.Time;

public interface TimeService {

	Page<TimeEntity> get(Time time, Pageable page, Link link);

	Time save(Time time, Link link);

	Time update(Time time, Link link);

	Boolean delete(Long id, Link link);

}

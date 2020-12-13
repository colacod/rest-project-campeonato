package br.com.project.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import br.com.project.constantes.ApplicationConstantes;
import br.com.project.entity.PlayoffEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.PlayoffRepository;
import br.com.project.resource.Playoff;
import br.com.project.service.PlayoffService;

@Service
@Transactional
public class PlayoffServiceImpl implements PlayoffService {

	@Autowired
	private PlayoffRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<PlayoffEntity> get(Playoff playoff, Pageable page, Link link) {
		PlayoffEntity entity = getPlayoffEntityBuilder(playoff);

		Page<PlayoffEntity> playoffs = repository.findAll(Example.of(entity), page);

		if (playoffs.isEmpty()) {
			throw new RegisterNotFoundException(playoff, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link);
		}

		return playoffs;
	}

	@Override
	public Playoff save(Playoff playoff, Link link) {
		repository.findById(playoff.getIdPlayoff()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(playoff, ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION,
					link);
		});

		PlayoffEntity entity = getPlayoffEntityBuilder(playoff);

		return modelMapper.map(repository.saveAndFlush(entity), Playoff.class);
	}

	@Override
	public Playoff update(Playoff playoff, Link link) {
		return repository.findById(playoff.getIdPlayoff()).map(entity -> {
			return modelMapper.map(repository.save(getPlayoffEntityBuilder(playoff)), Playoff.class);
		}).orElseThrow(() -> new RegisterNotFoundException(playoff,
				ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link));
	}

	@Override
	public Boolean delete(Long id, Link link) {
		return repository.findById(id).map(entity -> {
			repository.delete(entity);
			return Boolean.TRUE;
		}).orElseThrow(
				() -> new RegisterNotFoundException(id, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link));
	}

	private PlayoffEntity getPlayoffEntityBuilder(Playoff playoff) {
		return modelMapper.map(playoff, PlayoffEntity.class);
	}
}

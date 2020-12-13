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
import br.com.project.entity.TimeEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.TimeRepository;
import br.com.project.resource.Time;
import br.com.project.service.TimeService;

@Service
@Transactional
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<TimeEntity> get(Time time, Pageable page, Link link) {
		TimeEntity entity = getTimeEntityBuilder(time);

		Page<TimeEntity> times = repository.findAll(Example.of(entity), page);

		if (times.isEmpty()) {
			throw new RegisterNotFoundException(time, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link);
		}

		return times;
	}

	@Override
	public Time save(Time time, Link link) {
		repository.findById(time.getIdTime()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(time, ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION,
					link);
		});

		TimeEntity entity = getTimeEntityBuilder(time);

		return modelMapper.map(repository.saveAndFlush(entity), Time.class);
	}

	@Override
	public Time update(Time time, Link link) {
		return repository.findById(time.getIdTime()).map(entity -> {
			return modelMapper.map(repository.save(getTimeEntityBuilder(time)), Time.class);
		}).orElseThrow(() -> new RegisterNotFoundException(time, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION,
				link));
	}

	@Override
	public Boolean delete(Long id, Link link) {
		return repository.findById(id).map(entity -> {
			repository.delete(entity);
			return Boolean.TRUE;
		}).orElseThrow(
				() -> new RegisterNotFoundException(id, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link));
	}

	private TimeEntity getTimeEntityBuilder(Time time) {
		return modelMapper.map(time, TimeEntity.class);
	}
}

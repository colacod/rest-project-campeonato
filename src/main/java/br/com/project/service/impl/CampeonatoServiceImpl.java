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
import br.com.project.entity.CampeonatoEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.CampeonatoRepository;
import br.com.project.resource.Campeonato;
import br.com.project.service.CampeonatoService;

@Service
@Transactional
public class CampeonatoServiceImpl implements CampeonatoService {

	@Autowired
	private CampeonatoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<CampeonatoEntity> get(Campeonato campeonato, Pageable page, Link link) {
		CampeonatoEntity entity = getCampeonatoEntityBuilder(campeonato);

		Page<CampeonatoEntity> campeonatos = repository.findAll(Example.of(entity), page);

		if (campeonatos.isEmpty()) {
			throw new RegisterNotFoundException(campeonato, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION,
					link);
		}

		return campeonatos;
	}

	@Override
	public Campeonato save(Campeonato campeonato, Link link) {
		repository.findById(campeonato.getIdCampeonato()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(campeonato,
					ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION, link);
		});

		CampeonatoEntity entity = getCampeonatoEntityBuilder(campeonato);

		return modelMapper.map(repository.saveAndFlush(entity), Campeonato.class);
	}

	@Override
	public Campeonato update(Campeonato campeonato, Link link) {
		return repository.findById(campeonato.getIdCampeonato()).map(entity -> {
			return modelMapper.map(repository.save(getCampeonatoEntityBuilder(campeonato)), Campeonato.class);
		}).orElseThrow(() -> new RegisterNotFoundException(campeonato,
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

	private CampeonatoEntity getCampeonatoEntityBuilder(Campeonato campeonato) {
		return modelMapper.map(campeonato, CampeonatoEntity.class);
	}
}

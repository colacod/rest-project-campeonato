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
import br.com.project.entity.GrupoEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.GrupoRepository;
import br.com.project.resource.Grupo;
import br.com.project.service.GrupoService;

@Service
@Transactional
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private GrupoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<GrupoEntity> get(Grupo grupo, Pageable page, Link link) {
		GrupoEntity entity = getGrupoEntityBuilder(grupo);

		Page<GrupoEntity> grupos = repository.findAll(Example.of(entity), page);

		if (grupos.isEmpty()) {
			throw new RegisterNotFoundException(grupo, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION, link);
		}

		return grupos;
	}

	@Override
	public Grupo save(Grupo grupo, Link link) {
		repository.findById(grupo.getIdGrupo()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(grupo, ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION,
					link);
		});

		GrupoEntity entity = getGrupoEntityBuilder(grupo);

		return modelMapper.map(repository.saveAndFlush(entity), Grupo.class);
	}

	@Override
	public Grupo update(Grupo grupo, Link link) {
		return repository.findById(grupo.getIdGrupo()).map(entity -> {
			return modelMapper.map(repository.save(getGrupoEntityBuilder(grupo)), Grupo.class);
		}).orElseThrow(() -> new RegisterNotFoundException(grupo,
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

	private GrupoEntity getGrupoEntityBuilder(Grupo grupo) {
		return modelMapper.map(grupo, GrupoEntity.class);
	}
}

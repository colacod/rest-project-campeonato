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
import br.com.project.entity.GrupoVinculoEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.GrupoVinculoRepository;
import br.com.project.resource.GrupoVinculo;
import br.com.project.service.GrupoVinculoService;

@Service
@Transactional
public class GrupoVinculoServiceImpl implements GrupoVinculoService {

	@Autowired
	private GrupoVinculoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<GrupoVinculoEntity> get(GrupoVinculo grupoVinculo, Pageable page, Link link) {
		GrupoVinculoEntity entity = getGrupoVinculoEntityBuilder(grupoVinculo);

		Page<GrupoVinculoEntity> grupoVinculos = repository.findAll(Example.of(entity), page);

		if (grupoVinculos.isEmpty()) {
			throw new RegisterNotFoundException(grupoVinculo, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION,
					link);
		}

		return grupoVinculos;
	}

	@Override
	public GrupoVinculo save(GrupoVinculo grupoVinculo, Link link) {
		repository.findById(grupoVinculo.getIdGrupoVinculo()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(grupoVinculo,
					ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION, link);
		});

		GrupoVinculoEntity entity = getGrupoVinculoEntityBuilder(grupoVinculo);

		return modelMapper.map(repository.saveAndFlush(entity), GrupoVinculo.class);
	}

	@Override
	public GrupoVinculo update(GrupoVinculo grupoVinculo, Link link) {
		return repository.findById(grupoVinculo.getIdGrupoVinculo()).map(entity -> {
			return modelMapper.map(repository.save(getGrupoVinculoEntityBuilder(grupoVinculo)), GrupoVinculo.class);
		}).orElseThrow(() -> new RegisterNotFoundException(grupoVinculo,
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

	private GrupoVinculoEntity getGrupoVinculoEntityBuilder(GrupoVinculo grupoVinculo) {
		return modelMapper.map(grupoVinculo, GrupoVinculoEntity.class);
	}
}

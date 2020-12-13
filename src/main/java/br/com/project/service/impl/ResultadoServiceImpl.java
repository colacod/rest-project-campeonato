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
import br.com.project.entity.ResultadoEntity;
import br.com.project.exception.RecordAlreadyExistsException;
import br.com.project.exception.RegisterNotFoundException;
import br.com.project.repository.ResultadoRepository;
import br.com.project.resource.Resultado;
import br.com.project.service.ResultadoService;

@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService {

	@Autowired
	private ResultadoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<ResultadoEntity> get(Resultado resultado, Pageable page, Link link) {
		ResultadoEntity entity = getResultadoEntityBuilder(resultado);

		Page<ResultadoEntity> resultados = repository.findAll(Example.of(entity), page);

		if (resultados.isEmpty()) {
			throw new RegisterNotFoundException(resultado, ApplicationConstantes.LOG_REGISTER_NOT_FOUND_EXCEPTION,
					link);
		}

		return resultados;
	}

	@Override
	public Resultado save(Resultado resultado, Link link) {
		repository.findById(resultado.getIdResultado()).ifPresent(entity -> {
			throw new RecordAlreadyExistsException(resultado, ApplicationConstantes.LOG_RECORD_ALREADY_EXISTS_EXCEPTION,
					link);
		});

		ResultadoEntity entity = getResultadoEntityBuilder(resultado);

		return modelMapper.map(repository.saveAndFlush(entity), Resultado.class);
	}

	@Override
	public Resultado update(Resultado resultado, Link link) {
		return repository.findById(resultado.getIdResultado()).map(entity -> {
			return modelMapper.map(repository.save(getResultadoEntityBuilder(resultado)), Resultado.class);
		}).orElseThrow(() -> new RegisterNotFoundException(resultado,
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

	private ResultadoEntity getResultadoEntityBuilder(Resultado resultado) {
		return modelMapper.map(resultado, ResultadoEntity.class);
	}
}

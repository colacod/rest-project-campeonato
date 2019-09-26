package br.com.project.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.project.entity.GrupoVinculoEntity;
import br.com.project.repository.GrupoVinculoRepository;
import br.com.project.resource.GrupoVinculo;
import br.com.project.resource.Time;
import br.com.project.service.GrupoVinculoService;

@Service
@Transactional
public class GrupoVinculoServiceImpl implements GrupoVinculoService {

	@Autowired
	private GrupoVinculoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<GrupoVinculo> getGrupo(Integer idGrupo, Integer idCampeonato) {

		GrupoVinculoEntity exemploGrupoVinculo = new GrupoVinculoEntity();
		exemploGrupoVinculo.setIdGrupo(idGrupo);
		exemploGrupoVinculo.setIdCampeonatoGrupo(idCampeonato);

		List<GrupoVinculoEntity> grupoVinculoEntity = repository.findAll(Example.of(exemploGrupoVinculo));

		if (grupoVinculoEntity.isEmpty()) {
			return new ArrayList<>();
		}

		return grupoVinculoEntity.stream().map(source -> modelMapper.map(source, GrupoVinculo.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GrupoVinculo> getGrupos() {
		List<GrupoVinculoEntity> grupoVinculoEntity = repository.findAll();
		if (grupoVinculoEntity.isEmpty()) {
			return new ArrayList<>();
		}
		return grupoVinculoEntity.stream().map(source -> modelMapper.map(source, GrupoVinculo.class))
				.collect(Collectors.toList());
	}

	@Override
	public GrupoVinculo setGrupo(Integer idGrupo, Integer idTime, Integer idCampeonato) {
		GrupoVinculoEntity grupoVinculoEntity = new GrupoVinculoEntity();
		grupoVinculoEntity.setIdGrupo(idGrupo);
		grupoVinculoEntity.setIdTime(idTime);
		grupoVinculoEntity.setIdCampeonatoGrupo(idCampeonato);
		return modelMapper.map(repository.saveAndFlush(grupoVinculoEntity), GrupoVinculo.class);
	}

	@Override
	public List<Time> getTimesGrupos(Integer idCampeonato) {

		GrupoVinculoEntity exemploGrupoVinculo = new GrupoVinculoEntity();
		exemploGrupoVinculo.setIdCampeonatoGrupo(idCampeonato);

		List<GrupoVinculoEntity> grupoVinculoEntity = repository.findAll(Example.of(exemploGrupoVinculo));

		if (grupoVinculoEntity.isEmpty()) {
			return new ArrayList<>();
		}

		return grupoVinculoEntity.stream().map(source -> modelMapper.map(source.getTime(), Time.class))
				.sorted(Comparator.comparingInt(Time::getIdTime)).collect(Collectors.toList());
	}
}

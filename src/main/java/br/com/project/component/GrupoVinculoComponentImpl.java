package br.com.project.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.project.entity.GrupoVinculoEntity;
import br.com.project.repository.GrupoVinculoRepository;
import br.com.project.resource.GrupoVinculo;

@Component
@Transactional
public class GrupoVinculoComponentImpl implements GrupoVinculoComponent {

	@Autowired
	private GrupoVinculoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<GrupoVinculo> getGrupo(Integer idGrupo) {

		GrupoVinculoEntity exemploGrupoVinculo = new GrupoVinculoEntity();
		exemploGrupoVinculo.setIdGrupo(idGrupo);

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
	public GrupoVinculo setGrupo(Integer idGrupo, Integer idTime) {
		GrupoVinculoEntity grupoVinculoEntity = new GrupoVinculoEntity();
		grupoVinculoEntity.setIdGrupo(idGrupo);
		grupoVinculoEntity.setIdTime(idTime);
		return modelMapper.map(repository.saveAndFlush(grupoVinculoEntity), GrupoVinculo.class);
	}
}

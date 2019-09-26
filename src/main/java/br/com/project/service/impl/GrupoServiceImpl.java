package br.com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.entity.GrupoEntity;
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
	public Grupo getGrupo(Integer id) {
		Optional<GrupoEntity> grupo = repository.findById(id);
		if (grupo.isPresent()) {
			return modelMapper.map(grupo.get(), Grupo.class);
		}
		return null;
	}

	@Override
	public List<Grupo> getGrupos() {
		List<GrupoEntity> grupos = repository.findAll();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public List<Grupo> buscarTodosGrupos() {
		List<GrupoEntity> grupos = repository.buscarTodosGrupos();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public List<Grupo> buscarTodosGruposQuartas() {
		List<GrupoEntity> grupos = repository.buscarTodosGruposQuartas();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public List<Grupo> buscarTodosGruposSemis() {
		List<GrupoEntity> grupos = repository.buscarTodosGruposSemis();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public List<Grupo> buscarTodosGruposFinais() {
		List<GrupoEntity> grupos = repository.buscarTodosGruposFinais();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public List<Grupo> buscarTodosPlayoff() {
		List<GrupoEntity> grupos = repository.buscarTodosPlayoff();
		return convertEntityToResourceGrupo(grupos);
	}

	@Override
	public Grupo setGrupo(Grupo grupo) {
		GrupoEntity grupoEntity = new GrupoEntity();
		grupoEntity.setNomeGrupo(grupo.getNomeGrupo());
		return modelMapper.map(repository.saveAndFlush(grupoEntity), Grupo.class);
	}

	private List<Grupo> convertEntityToResourceGrupo(List<GrupoEntity> grupos) {
		if (grupos.isEmpty()) {
			return new ArrayList<>();
		}
		return grupos.stream().map(source -> modelMapper.map(source, Grupo.class)).collect(Collectors.toList());
	}
}

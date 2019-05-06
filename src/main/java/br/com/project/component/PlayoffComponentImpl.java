package br.com.project.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.project.entity.PlayoffEntity;
import br.com.project.repository.PlayoffRepository;
import br.com.project.resource.Playoff;

@Component
@Transactional
public class PlayoffComponentImpl implements PlayoffComponent {

	@Autowired
	private PlayoffRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Playoff> getPlayoff(Integer idCampeonato) {

		PlayoffEntity examploPlayoff = new PlayoffEntity();
		examploPlayoff.setIdCampeonatoPlayoff(idCampeonato);

		List<PlayoffEntity> playoffsEntity = repository.findAll(Example.of(examploPlayoff));

		if (playoffsEntity.isEmpty()) {
			return new ArrayList<>();
		}

		return playoffsEntity.stream().map(source -> modelMapper.map(source, Playoff.class))
				.collect(Collectors.toList());
	}

	@Override
	public Playoff setPlayoff(Playoff playoff) {
		PlayoffEntity playoffEntity = new PlayoffEntity();
		playoffEntity.setIdCampeonatoPlayoff(playoff.getIdCampeonatoPlayoff());
		playoffEntity.setIdGrupo(playoff.getIdGrupo());
		playoffEntity.setIdTimeUm(playoff.getIdTimeUm());
		playoffEntity.setIdTimeDois(playoff.getIdTimeDois());
		return modelMapper.map(repository.saveAndFlush(playoffEntity), Playoff.class);
	}
}

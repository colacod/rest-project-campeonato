package br.com.project.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.ApplicationConstantes;
import br.com.project.entity.CampeonatoEntity;
import br.com.project.repository.CampeonatoRepository;
import br.com.project.resource.Campeonato;

@Component
@Transactional
public class CampeonatoComponentImpl implements CampeonatoComponent {

	@Autowired
	private CampeonatoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Campeonato getCampeonato(Integer id) {
		CampeonatoEntity campeonatoEntity = getCampeonatoEntity(id);
		if (Objects.nonNull(campeonatoEntity)) {
			return modelMapper.map(campeonatoEntity, Campeonato.class);
		}
		return null;
	}

	@Override
	public List<Campeonato> getCampeonatos() {
		List<CampeonatoEntity> campeonatosEntity = repository.findAll();
		if (campeonatosEntity.isEmpty()) {
			return new ArrayList<>();
		}
		return campeonatosEntity.stream().map(source -> modelMapper.map(source, Campeonato.class))
				.collect(Collectors.toList());
	}

	@Override
	public Campeonato saveCampeonato(Campeonato campeonato) {
		CampeonatoEntity campeonatoEntity = new CampeonatoEntity();
		campeonatoEntity.setLimiteTimes(campeonato.getLimiteTimes());
		campeonatoEntity.setCampeonatoNome(campeonato.getCampeonatoNome());
		campeonatoEntity.setTotalTimes(ApplicationConstantes.INTEGER_ZERO);
		return modelMapper.map(repository.saveAndFlush(campeonatoEntity), Campeonato.class);
	}

	@Override
	public Campeonato addTotalCampeonato(Integer idCampeonato) {
		CampeonatoEntity campeonatoEntity = getCampeonatoEntity(idCampeonato);
		if (Objects.nonNull(campeonatoEntity)) {
			campeonatoEntity.setTotalTimes(campeonatoEntity.getTotalTimes() + 1);
			return modelMapper.map(repository.saveAndFlush(campeonatoEntity), Campeonato.class);
		}
		return null;
	}

	private CampeonatoEntity getCampeonatoEntity(Integer id) {
		Optional<CampeonatoEntity> campeonatoEntity = repository.findById(id);
		if (campeonatoEntity.isPresent()) {
			return campeonatoEntity.get();
		}
		return null;
	}
}

package br.com.project.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.project.entity.ResultadoEntity;
import br.com.project.repository.ResultadoRepository;
import br.com.project.resource.Resultado;

@Component
@Transactional
public class ResultadoComponentImpl implements ResultadoComponent {

	@Autowired
	private ResultadoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Resultado> getResultado(Integer idCampeonato) {

		ResultadoEntity exemploResultado = new ResultadoEntity();
		exemploResultado.setIdCampeonatoResultado(idCampeonato);

		List<ResultadoEntity> resultadosEntity = repository.findAll(Example.of(exemploResultado));

		if (resultadosEntity.isEmpty()) {
			return new ArrayList<>();
		}

		return resultadosEntity.stream().map(source -> modelMapper.map(source, Resultado.class))
				.collect(Collectors.toList());
	}

	@Override
	public Resultado setResultado(Resultado resultado) {
		ResultadoEntity resultadoEntity = new ResultadoEntity();
		resultadoEntity.setResultadoTimeUm(resultado.getResultadoTimeUm());
		resultadoEntity.setResultadoTimeDois(resultado.getResultadoTimeDois());
		resultadoEntity.setIdCampeonatoResultado(resultado.getIdCampeonatoResultado());
		resultadoEntity.setIdTimeUm(resultado.getIdTimeUm());
		resultadoEntity.setIdTimeDois(resultado.getIdTimeDois());
		resultadoEntity.setIdGrupo(resultado.getIdGrupo());
		resultadoEntity.setIdTimeVencedor(resultado.getIdTimeVencedor());

		return modelMapper.map(repository.saveAndFlush(resultadoEntity), Resultado.class);
	}

	@Override
	public List<Resultado> getResultadoPorGrupo(Integer idGrupo) {

		ResultadoEntity exemploResultado = new ResultadoEntity();
		exemploResultado.setIdGrupo(idGrupo);

		List<ResultadoEntity> resultadosEntity = repository.findAll(Example.of(exemploResultado));

		if (resultadosEntity.isEmpty()) {
			return new ArrayList<>();
		}

		return resultadosEntity.stream().map(source -> modelMapper.map(source, Resultado.class))
				.collect(Collectors.toList());
	}
}

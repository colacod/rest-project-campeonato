package br.com.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.component.CampeonatoComponent;
import br.com.project.component.GrupoComponent;
import br.com.project.component.GrupoVinculoComponent;
import br.com.project.component.PlayoffComponent;
import br.com.project.component.ResultadoComponent;
import br.com.project.component.TimeComponent;
import br.com.project.resource.Grupo;
import br.com.project.resource.Playoff;
import br.com.project.resource.Resultado;
import br.com.project.resource.Time;

@Service
public class CampeonatoServiceImpl implements CampeonatoService {

	@Autowired
	private ResultadoComponent resultadoComponent;

	@Autowired
	private TimeComponent timeComponent;

	@Autowired
	private GrupoComponent grupoComponent;

	@Autowired
	private GrupoVinculoComponent grupoVinculoComponent;

	@Autowired
	private CampeonatoComponent campeonatoComponent;

	@Autowired
	private PlayoffComponent playoffComponent;

	@Override
	public void criarGrupoCampeonato(Integer idCampeonato) {

		List<Time> times = timeComponent.getTimes();
		List<Grupo> grupos = grupoComponent.buscarTodosGrupos();
		Integer quantidadeTimesPorGrupo = times.size() / grupos.size();

		Integer quantidadeMaxima = quantidadeTimesPorGrupo;
		Integer quantidadeInicial = 0;

		Collections.shuffle(times);

		for (Grupo grupo : grupos) {
			while (quantidadeInicial < quantidadeMaxima) {
				grupoVinculoComponent.setGrupo(grupo.getIdGrupo(), times.get(quantidadeInicial).getIdTime());
				campeonatoComponent.addTotalCampeonato(idCampeonato);
				quantidadeInicial++;
			}
			quantidadeInicial = quantidadeMaxima;
			quantidadeMaxima += quantidadeTimesPorGrupo;
		}
	}

	@Override
	public void criarPlayoffCampeonato(Integer idCampeonato, Integer idGrupo, Integer limiteDeTimes) {

		List<Grupo> grupos = grupoComponent.buscarTodosGrupos();
		List<Time> times = new ArrayList<>();

		for (Grupo grupo : grupos) {
			List<Resultado> resultadosPorGrupo = resultadoComponent.getResultadoPorGrupo(grupo.getIdGrupo());
			Map<Integer, Integer> pontosPorGrupo = somarPontosGrupos(resultadosPorGrupo);
			times.addAll(pegarTimesComMaisPontos(pontosPorGrupo, limiteDeTimes));
		}

		Collections.shuffle(times);

		final Integer quantidadeMaxima = times.size();

		List<Time> timesUm = times.subList(0, quantidadeMaxima / 2);
		List<Time> timesDois = times.subList(quantidadeMaxima / 2, quantidadeMaxima);

		for (int i = 0; i < timesUm.size(); i++) {
			Playoff playoff = new Playoff();
			playoff.setIdCampeonatoPlayoff(idCampeonato);
			playoff.setIdGrupo(idGrupo);
			playoff.setIdTimeUm(timesUm.get(i).getIdTime());
			playoff.setIdTimeDois(timesDois.get(i).getIdTime());
			playoffComponent.setPlayoff(playoff);
		}
	}

	/**
	 * Metodo responsavel por somar os pontos de cada resultado por grupo
	 * 
	 * @param resultadosPorGrupo, lista dos resultados do grupo
	 * @return map com os ids dos times e seus pontos
	 */
	private Map<Integer, Integer> somarPontosGrupos(List<Resultado> resultadosPorGrupo) {

		if (resultadosPorGrupo.isEmpty()) {
			return new HashMap<>();
		}

		Map<Integer, Integer> pontosPorTime = new HashMap<>();

		for (Resultado resultado : resultadosPorGrupo) {
			Integer idTimeVencedor = resultado.getIdTimeVencedor();

			if (pontosPorTime.containsKey(idTimeVencedor)) {
				pontosPorTime.put(idTimeVencedor, pontosPorTime.get(idTimeVencedor) + 1);
			} else {
				pontosPorTime.put(idTimeVencedor, 1);
			}
		}

		return pontosPorTime;
	}

	/**
	 * Metodo responsavel por retornar os times com mais pontos
	 * 
	 * @param pontosPorGrupo, objeto que contem os pontos de cada equipe
	 * @param limiteDeTimes, limite de times que sera retornado
	 * @return lista de times com mais pontos
	 */
	private List<Time> pegarTimesComMaisPontos(Map<Integer, Integer> pontosPorGrupo, Integer limiteDeTimes) {

		if (pontosPorGrupo.isEmpty()) {
			return new ArrayList<>();
		}

		return pontosPorGrupo.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(limiteDeTimes).map(source -> timeComponent.getTime(source.getKey()))
				.collect(Collectors.toList());
	}
}

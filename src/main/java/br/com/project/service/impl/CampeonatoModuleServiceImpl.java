package br.com.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.resource.Campeonato;
import br.com.project.resource.Grupo;
import br.com.project.resource.Playoff;
import br.com.project.resource.Resultado;
import br.com.project.resource.Time;
import br.com.project.service.CampeonatoModuleService;
import br.com.project.service.CampeonatoService;
import br.com.project.service.GrupoService;
import br.com.project.service.GrupoVinculoService;
import br.com.project.service.PlayoffService;
import br.com.project.service.ResultadoService;
import br.com.project.service.TimeService;

@Service
public class CampeonatoModuleServiceImpl implements CampeonatoModuleService {

	@Autowired
	private ResultadoService resultadoComponent;

	@Autowired
	private TimeService timeComponent;

	@Autowired
	private GrupoService grupoComponent;

	@Autowired
	private GrupoVinculoService grupoVinculoComponent;

	@Autowired
	private CampeonatoService campeonatoComponent;

	@Autowired
	private PlayoffService playoffComponent;

	@Override
	public void criarGrupoCampeonato(Integer idCampeonato) {

		List<Time> times = timeComponent.getTimes();
		List<Grupo> grupos = grupoComponent.buscarTodosGrupos();
		Campeonato campeonato = campeonatoComponent.getCampeonato(idCampeonato);

		List<Time> timeCampeonato = times.subList(0, campeonato.getLimiteTimes());

		Integer quantidadeTimesPorGrupo = 4;
		Integer quantidadeGrupos = campeonato.getLimiteTimes() / quantidadeTimesPorGrupo;

		Integer quantidadeMaxima = quantidadeTimesPorGrupo;
		Integer quantidadeInicial = 0;

		Collections.shuffle(timeCampeonato);

		for (int i = 0; i < quantidadeGrupos; i++) {
			while (quantidadeInicial < quantidadeMaxima) {
				grupoVinculoComponent.setGrupo(grupos.get(i).getIdGrupo(),
						timeCampeonato.get(quantidadeInicial).getIdTime(), idCampeonato);
				campeonatoComponent.addTotalCampeonato(idCampeonato);
				quantidadeInicial++;
			}
			quantidadeInicial = quantidadeMaxima;
			quantidadeMaxima += quantidadeTimesPorGrupo;
		}
	}

	@Override
	public void criarPlayoffCampeonato(Integer idCampeonato, Integer idProximoGrupo, Integer limiteTimePorGrupo) {
		criarPlayoffCampeonato(grupoComponent.buscarTodosGrupos(), idCampeonato, idProximoGrupo, limiteTimePorGrupo);
	}

	@Override
	public void criarSequencePlayoffCampeonato(Integer idCampeonato, Integer idGrupo, Integer idProximoGrupo,
			Integer limiteTimePorGrupo) {
		criarPlayoffCampeonato(Arrays.asList(grupoComponent.getGrupo(idGrupo)), idCampeonato, idProximoGrupo,
				limiteTimePorGrupo);
	}

	/**
	 * Metodo responsavel por criar as playoff de acordo com o seu grupo e id do
	 * proximo grupo
	 * 
	 * @param grupos, lista do grupo atual
	 * @param idCampeonato, id do campeonato atual
	 * @param idProximoGrupo, id do proximo grupo das playoff
	 * @param limiteTimePorGrupo, limite de times que sera adicionado na playoff
	 */
	private void criarPlayoffCampeonato(List<Grupo> grupos, Integer idCampeonato, Integer idProximoGrupo,
			Integer limiteTimePorGrupo) {

		List<Time> times = new ArrayList<>();

		for (Grupo grupo : grupos) {
			List<Resultado> resultadosPorGrupo = resultadoComponent.getResultadoPorGrupo(grupo.getIdGrupo());
			Map<Integer, Integer> pontosPorGrupo = somarPontosGrupos(resultadosPorGrupo);
			times.addAll(pegarTimesComMaisPontos(pontosPorGrupo, limiteTimePorGrupo));
		}

		Collections.shuffle(times);

		final Integer quantidadeMaxima = times.size();

		List<Time> timesUm = times.subList(0, quantidadeMaxima / 2);
		List<Time> timesDois = times.subList(quantidadeMaxima / 2, quantidadeMaxima);

		for (int i = 0; i < timesUm.size(); i++) {
			Playoff playoff = new Playoff();
			playoff.setIdCampeonatoPlayoff(idCampeonato);
			playoff.setIdGrupo(idProximoGrupo);
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

package br.com.project.resource;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ApiModel
public class Campeonato {

	@NotNull
	private Long idCampeonato;

	@NotNull
	private Integer intLimiteTimes;

	@NotNull
	private Integer intTotalTimes;

	@NotNull
	@Size(max = 255)
	private String nmCampeonato;

	private List<GrupoVinculo> tbGrupoVinculos;

	private List<Playoff> tbPlayoffs;

	private List<Resultado> tbResultados;

}

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
public class Time {

	@NotNull
	private Long idTime;

	@NotNull
	@Size(max = 255)
	private String nmTimeNome;

	@NotNull
	@Size(max = 5)
	private String txtTimeAbrv;

	private List<GrupoVinculo> tbGrupoVinculos;

	private List<Playoff> tbPlayoffs1;

	private List<Playoff> tbPlayoffs2;

	private List<Resultado> tbResultados1;

	private List<Resultado> tbResultados2;

	private List<Resultado> tbResultados3;

}

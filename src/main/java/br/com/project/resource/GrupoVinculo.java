package br.com.project.resource;

import javax.validation.constraints.NotNull;

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
public class GrupoVinculo {

	@NotNull
	private Long idGrupoVinculo;

	private Campeonato tbCampeonato;

	private Grupo tbGrupo;

	private Time tbTime;

}

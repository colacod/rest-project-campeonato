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
public class Grupo {

	@NotNull
	private Long idGrupo;

	@NotNull
	@Size(max = 255)
	private String nmGrupo;

	private List<GrupoVinculo> tbGrupoVinculos;

	private List<Playoff> tbPlayoffs;

	private List<Resultado> tbResultados;

}

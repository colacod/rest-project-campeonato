package br.com.project.resource;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Resultado implements Serializable {

	private static final long serialVersionUID = 3484318743293909656L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idResultado;

	@NotNull
	@ApiModelProperty(notes = "Placar do jogo do time um", example = "16", position = 2)
	private Integer resultadoTimeUm;

	@NotNull
	@ApiModelProperty(notes = "Placar do jogo do time dois", example = "5", position = 3)
	private Integer resultadoTimeDois;

	@NotNull
	@ApiModelProperty(notes = "Identificador do campeonato", example = "5", position = 4)
	private Integer idCampeonatoResultado;

	@NotNull
	@ApiModelProperty(notes = "Identificador do time um", example = "5", position = 5)
	private Integer idTimeUm;

	@NotNull
	@ApiModelProperty(notes = "Identificador do time dois", example = "5", position = 6)
	private Integer idTimeDois;

	@NotNull
	@ApiModelProperty(notes = "Identificador do time vencedor", example = "5", position = 7)
	private Integer idTimeVencedor;

	@NotNull
	@ApiModelProperty(notes = "Identificador do grupo", example = "5", position = 8)
	private Integer idGrupo;

	@ApiModelProperty(notes = "Campeonato", position = 9)
	private Campeonato campeonato;

	@ApiModelProperty(notes = "Time um", position = 10)
	private Time timeUm;

	@ApiModelProperty(notes = "Time dois", position = 11)
	private Time timeDois;

	@ApiModelProperty(notes = "Grupo", position = 12)
	private Grupo grupo;

	@ApiModelProperty(notes = "Time Vencedor", position = 13)
	private Time timeVencedor;
}

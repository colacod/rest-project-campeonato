package br.com.project.resource;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Playoff implements Serializable {

	private static final long serialVersionUID = 1557589759410331907L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idPlayoff;

	@ApiModelProperty(notes = "Identificador do campeonato", example = "1", position = 2)
	private Integer idCampeonatoPlayoff;

	@ApiModelProperty(notes = "Identificador do grupo", example = "1", position = 3)
	private Integer idGrupo;

	@ApiModelProperty(notes = "Identificador do time um", example = "1", position = 4)
	private Integer idTimeUm;

	@ApiModelProperty(notes = "Identificador do time dois", example = "1", position = 5)
	private Integer idTimeDois;

	@ApiModelProperty(notes = "Campeonato", position = 6)
	private Campeonato campeonato;

	@ApiModelProperty(notes = "Time um", position = 7)
	private Time timeUm;

	@ApiModelProperty(notes = "Time dois", position = 8)
	private Time timeDois;

	@ApiModelProperty(notes = "Grupo", position = 9)
	private Grupo grupo;

}

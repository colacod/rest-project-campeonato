package br.com.project.resource;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
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
public class Campeonato implements Serializable {

	private static final long serialVersionUID = -3423367598345731796L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idCampeonato;

	@ApiModelProperty(notes = "Total de times inscrito", example = "1", position = 2)
	private Integer totalTimes;

	@NotNull
	@ApiModelProperty(notes = "Limite de times por campeonato", example = "80", position = 3)
	private Integer limiteTimes;

	@NotBlank
	@ApiModelProperty(notes = "Nome do campeonato", example = "ESL PRO LEAGUE", position = 4)
	private String campeonatoNome;

}

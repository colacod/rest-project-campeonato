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
public class GrupoVinculo implements Serializable {

	private static final long serialVersionUID = -3423367598345731796L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idGrupoVinculo;

	@ApiModelProperty(notes = "Grupo", position = 2)
	private Grupo grupo;

	@ApiModelProperty(notes = "Time", position = 3)
	private Time time;

}

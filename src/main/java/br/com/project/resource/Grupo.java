package br.com.project.resource;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Grupo implements Serializable {

	private static final long serialVersionUID = -3423367598345731796L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idGrupo;

	@NotBlank
	@ApiModelProperty(notes = "Nome do grupo", example = "GRUPO A", position = 2)
	private String nomeGrupo;

}

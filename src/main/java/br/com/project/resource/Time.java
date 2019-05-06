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
public class Time implements Serializable {

	private static final long serialVersionUID = -5340176615737396679L;

	@ApiModelProperty(notes = "Identificador", example = "1", position = 1)
	private Integer idTime;

	@NotBlank
	@ApiModelProperty(notes = "Nome do time", example = "Made'n Brazil", position = 2)
	private String timeNome;

	@NotBlank
	@ApiModelProperty(notes = "Nome do time abreviado", example = "MIBR", position = 3)
	private String timeAbreviado;

}

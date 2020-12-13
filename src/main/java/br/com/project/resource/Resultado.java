package br.com.project.resource;

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
public class Resultado {

	private Long idResultado;

	private Integer intResultadoTimeDois;

	private Integer intResultadoTimeUm;

	private Campeonato tbCampeonato;

	private Grupo tbGrupo;

	private Time tbTime2;

	private Time tbTime1;

	private Time tbTime3;

}

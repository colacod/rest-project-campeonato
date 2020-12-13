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
public class Playoff {

	private Long idPlayoff;

	private Campeonato tbCampeonato;

	private Grupo tbGrupo;

	private Time tbTime2;

	private Time tbTime1;

}

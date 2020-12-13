package br.com.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_resultado database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_RESULTADO", schema = "db")
public class ResultadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESULTADO", unique = true, nullable = false)
	private Long idResultado;

	@Column(name = "INT_RESULTADO_TIME_DOIS", nullable = false)
	private Integer intResultadoTimeDois;

	@Column(name = "INT_RESULTADO_TIME_UM", nullable = false)
	private Integer intResultadoTimeUm;

	// bi-directional many-to-one association to TbCampeonato
	@ManyToOne
	@JoinColumn(name = "ID_CAMPEONATO_RESULTADO", nullable = false)
	private CampeonatoEntity tbCampeonato;

	// bi-directional many-to-one association to TbGrupo
	@ManyToOne
	@JoinColumn(name = "ID_GRUPO", nullable = false)
	private GrupoEntity tbGrupo;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME_DOIS", nullable = false)
	private TimeEntity tbTime2;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME_UM", nullable = false)
	private TimeEntity tbTime1;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME_VENCEDOR", nullable = false)
	private TimeEntity tbTime3;

}
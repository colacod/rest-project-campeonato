package br.com.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_campeonato database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_CAMPEONATO", schema = "db")
public class CampeonatoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAMPEONATO", unique = true, nullable = false)
	private Long idCampeonato;

	@Column(name = "INT_LIMITE_TIMES", nullable = false)
	private Integer intLimiteTimes;

	@Column(name = "INT_TOTAL_TIMES", nullable = false)
	private Integer intTotalTimes;

	@Column(name = "NM_CAMPEONATO", nullable = false, length = 255)
	private String nmCampeonato;

	// bi-directional many-to-one association to TbGrupoVinculo
	@OneToMany(mappedBy = "tbCampeonato")
	private List<GrupoVinculoEntity> tbGrupoVinculos;

	// bi-directional many-to-one association to TbPlayoff
	@OneToMany(mappedBy = "tbCampeonato")
	private List<PlayoffEntity> tbPlayoffs;

	// bi-directional many-to-one association to TbResultado
	@OneToMany(mappedBy = "tbCampeonato")
	private List<ResultadoEntity> tbResultados;

}
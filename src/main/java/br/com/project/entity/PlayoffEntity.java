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
 * The persistent class for the tb_playoff database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_PLAYOFF", schema = "db")
public class PlayoffEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLAYOFF", unique = true, nullable = false)
	private Long idPlayoff;

	// bi-directional many-to-one association to TbCampeonato
	@ManyToOne
	@JoinColumn(name = "ID_CAMPEONATO_PLAYOFF", nullable = false)
	private CampeonatoEntity tbCampeonato;

	// bi-directional many-to-one association to TbGrupo
	@ManyToOne
	@JoinColumn(name = "id_grupo", nullable = false)
	private GrupoEntity tbGrupo;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME_DOIS", nullable = false)
	private TimeEntity tbTime2;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME_UM", nullable = false)
	private TimeEntity tbTime1;

}
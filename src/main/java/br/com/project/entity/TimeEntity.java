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
 * The persistent class for the tb_time database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_TIME", schema = "db")
public class TimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIME", unique = true, nullable = false)
	private Long idTime;

	@Column(name = "NM_TIME_NOME", nullable = false, length = 255)
	private String nmTimeNome;

	@Column(name = "TXT_TIME_ABRV", nullable = false, length = 5)
	private String txtTimeAbrv;

	// bi-directional many-to-one association to TbGrupoVinculo
	@OneToMany(mappedBy = "tbTime")
	private List<GrupoVinculoEntity> tbGrupoVinculos;

	// bi-directional many-to-one association to TbPlayoff
	@OneToMany(mappedBy = "tbTime1")
	private List<PlayoffEntity> tbPlayoffs1;

	// bi-directional many-to-one association to TbPlayoff
	@OneToMany(mappedBy = "tbTime2")
	private List<PlayoffEntity> tbPlayoffs2;

	// bi-directional many-to-one association to TbResultado
	@OneToMany(mappedBy = "tbTime1")
	private List<ResultadoEntity> tbResultados1;

	// bi-directional many-to-one association to TbResultado
	@OneToMany(mappedBy = "tbTime2")
	private List<ResultadoEntity> tbResultados2;

	// bi-directional many-to-one association to TbResultado
	@OneToMany(mappedBy = "tbTime3")
	private List<ResultadoEntity> tbResultados3;

}
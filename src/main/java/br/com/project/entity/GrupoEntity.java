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
 * The persistent class for the tb_grupo database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_GRUPO", schema = "db")
public class GrupoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO", unique = true, nullable = false)
	private Long idGrupo;

	@Column(name = "NM_GRUPO", nullable = false, length = 255)
	private String nmGrupo;

	// bi-directional many-to-one association to TbGrupoVinculo
	@OneToMany(mappedBy = "tbGrupo")
	private List<GrupoVinculoEntity> tbGrupoVinculos;

	// bi-directional many-to-one association to TbPlayoff
	@OneToMany(mappedBy = "tbGrupo")
	private List<PlayoffEntity> tbPlayoffs;

	// bi-directional many-to-one association to TbResultado
	@OneToMany(mappedBy = "tbGrupo")
	private List<ResultadoEntity> tbResultados;

}
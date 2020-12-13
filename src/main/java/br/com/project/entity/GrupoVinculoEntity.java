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
 * The persistent class for the tb_grupo_vinculo database table.
 * 
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_GRUPO_VINCULO", schema = "db")
public class GrupoVinculoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO_VINCULO", unique = true, nullable = false)
	private Long idGrupoVinculo;

	// bi-directional many-to-one association to TbCampeonato
	@ManyToOne
	@JoinColumn(name = "ID_CAMPEONATO_GRUPO", nullable = false)
	private CampeonatoEntity tbCampeonato;

	// bi-directional many-to-one association to TbGrupo
	@ManyToOne
	@JoinColumn(name = "ID_GRUPO", nullable = false)
	private GrupoEntity tbGrupo;

	// bi-directional many-to-one association to TbTime
	@ManyToOne
	@JoinColumn(name = "ID_TIME", nullable = false)
	private TimeEntity tbTime;

}
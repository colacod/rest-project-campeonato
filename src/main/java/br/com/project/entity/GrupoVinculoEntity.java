package br.com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_GRUPO_VINCULO")
public class GrupoVinculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO_VINCULO", unique = true, nullable = false)
	private Integer idGrupoVinculo;

	@Column(name = "ID_GRUPO", nullable = false)
	private Integer idGrupo;

	@Column(name = "ID_TIME", nullable = false)
	private Integer idTime;

	@OneToOne
	@JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO", insertable = false, updatable = false, nullable = false)
	private GrupoEntity grupo;

	@OneToOne
	@JoinColumn(name = "ID_TIME", referencedColumnName = "ID_TIME", insertable = false, updatable = false, nullable = false)
	private TimeEntity time;

}

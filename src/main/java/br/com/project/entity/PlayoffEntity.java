package br.com.project.entity;

import java.io.Serializable;

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
@Table(name = "TB_PLAYOFF")
public class PlayoffEntity implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 2798497671499948770L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLAYOFF", unique = true, nullable = false)
	private Integer idPlayoff;

	@Column(name = "ID_CAMPEONATO_PLAYOFF", nullable = false)
	private Integer idCampeonatoPlayoff;

	@Column(name = "ID_GRUPO", nullable = false)
	private Integer idGrupo;

	@Column(name = "ID_TIME_UM", nullable = false)
	private Integer idTimeUm;

	@Column(name = "ID_TIME_DOIS", nullable = false)
	private Integer idTimeDois;

	@OneToOne
	@JoinColumn(name = "ID_CAMPEONATO_PLAYOFF", referencedColumnName = "ID_CAMPEONATO", insertable = false, updatable = false, nullable = false)
	private CampeonatoEntity campeonato;

	@OneToOne
	@JoinColumn(name = "ID_TIME_UM", referencedColumnName = "ID_TIME", insertable = false, updatable = false, nullable = false)
	private TimeEntity timeUm;

	@OneToOne
	@JoinColumn(name = "ID_TIME_DOIS", referencedColumnName = "ID_TIME", insertable = false, updatable = false, nullable = false)
	private TimeEntity timeDois;

	@OneToOne
	@JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO", insertable = false, updatable = false, nullable = false)
	private GrupoEntity grupo;

}

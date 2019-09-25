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
@Table(name = "TB_RESULTADO")
public class ResultadoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESULTADO", unique = true, nullable = false)
	private Integer idResultado;

	@Column(name = "INT_RESULTADO_TIME_UM", nullable = false)
	private Integer resultadoTimeUm;

	@Column(name = "INT_RESULTADO_TIME_DOIS", nullable = false)
	private Integer resultadoTimeDois;

	@Column(name = "ID_CAMPEONATO_RESULTADO", nullable = false)
	private Integer idCampeonatoResultado;

	@Column(name = "ID_TIME_UM", nullable = false)
	private Integer idTimeUm;

	@Column(name = "ID_TIME_DOIS", nullable = false)
	private Integer idTimeDois;

	@Column(name = "ID_GRUPO", nullable = false)
	private Integer idGrupo;

	@Column(name = "ID_TIME_VENCEDOR", nullable = false)
	private Integer idTimeVencedor;

	@OneToOne
	@JoinColumn(name = "ID_CAMPEONATO_RESULTADO", referencedColumnName = "ID_CAMPEONATO", insertable = false, updatable = false, nullable = false)
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

	@OneToOne
	@JoinColumn(name = "ID_TIME_VENCEDOR", referencedColumnName = "ID_TIME", insertable = false, updatable = false, nullable = false)
	private TimeEntity timeVencedor;

}

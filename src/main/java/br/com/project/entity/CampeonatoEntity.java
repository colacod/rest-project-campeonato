package br.com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CAMPEONATO")
public class CampeonatoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAMPEONATO", unique = true, nullable = false)
	private Integer idCampeonato;

	@Column(name = "INT_TOTAL_TIMES", nullable = false)
	private Integer totalTimes;

	@Column(name = "INT_LIMITE_TIMES", nullable = false)
	private Integer limiteTimes;

	@Column(name = "NM_CAMPEONATO", nullable = false, length = 255)
	private String campeonatoNome;

}

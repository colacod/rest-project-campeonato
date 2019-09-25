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
@Table(name = "TB_TIME")
public class TimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIME", unique = true, nullable = false)
	private Integer idTime;

	@Column(name = "NM_TIME_NOME", nullable = false, length = 255)
	private String timeNome;

	@Column(name = "TXT_TIME_ABRV", nullable = false, length = 5)
	private String timeAbreviado;

}

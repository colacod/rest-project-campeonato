package br.com.project.entity;

import java.io.Serializable;

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
@Table(name = "TB_GRUPO")
public class GrupoEntity implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -3066553591680759405L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO", unique = true, nullable = false)
	private Integer idGrupo;

	@Column(name = "NM_GRUPO", nullable = false, length = 255)
	private String nomeGrupo;

}

package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.entity.GrupoEntity;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {

//	@Query("SELECT g FROM GrupoEntity g WHERE g.nomeGrupo LIKE '%GRUPO%'")
//	List<GrupoEntity> buscarTodosGrupos();
//
//	@Query("SELECT g FROM GrupoEntity g WHERE g.nomeGrupo NOT LIKE '%GRUPO%'")
//	List<GrupoEntity> buscarTodosPlayoff();

}

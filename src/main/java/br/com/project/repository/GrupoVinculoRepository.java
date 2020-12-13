package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.entity.GrupoVinculoEntity;

@Repository
public interface GrupoVinculoRepository extends JpaRepository<GrupoVinculoEntity, Long> {

}

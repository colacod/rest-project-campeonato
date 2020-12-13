package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.entity.CampeonatoEntity;

@Repository
public interface CampeonatoRepository extends JpaRepository<CampeonatoEntity, Long> {

}

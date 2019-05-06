package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.entity.ResultadoEntity;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Integer> {

}

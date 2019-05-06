package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.entity.TimeEntity;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Integer> {

}

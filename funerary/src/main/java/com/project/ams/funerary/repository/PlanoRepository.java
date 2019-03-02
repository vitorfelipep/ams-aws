package com.project.ams.funerary.repository;

import com.project.ams.funerary.domain.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
	
	Optional<Plano> findByNameIgnoreCase(String name);
}

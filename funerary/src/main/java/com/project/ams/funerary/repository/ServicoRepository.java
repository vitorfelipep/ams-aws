package com.project.ams.funerary.repository;

import com.project.ams.funerary.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author vitor
 *
 */
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
	Optional<Servico> findByNomeIgnoreCase(String nome);
}

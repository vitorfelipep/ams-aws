package com.project.ams.funerary.repository;

import com.project.ams.funerary.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author vitor
 *
 */
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
	
	List<Optional<Dependente>> findByCpfIgnoreCase(String cpf);
}

package com.project.ams.funerary.repository;

import com.project.ams.funerary.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author vitor
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByPessoaCpfIgnoreCase(String cpf);

}

package com.project.ams.funerary.repository;

import com.project.ams.funerary.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Produtos extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNameIgnoreCase(String nome);

    Optional<Produto> findBySkuIgnoreCase(String sku);
}

package com.project.ams.funerary.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "estado")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado implements Serializable {

	private static final long serialVersionUID = -9057868046279979881L;
	
	@Id
	@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy = "estado", fetch = FetchType.EAGER)
	private List<Cidade> cidades;
}

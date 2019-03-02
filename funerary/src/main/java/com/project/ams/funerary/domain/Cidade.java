package com.project.ams.funerary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "cidade")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 778225379678569844L;
	
	@Id
	@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade")
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_estado")
	@JsonIgnore
	private Estado estado;
}

package com.project.ams.funerary.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "telefone")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone implements Serializable {

	private static final long serialVersionUID = 6883725996981761814L;
	
	@Id
	@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "telefone_residencial")
	private String telefoneResidencial;
	
	@Column(name = "telefone_comercial")
	private String telefoneComercial;
	
	@NotEmpty
	private String celular;
}

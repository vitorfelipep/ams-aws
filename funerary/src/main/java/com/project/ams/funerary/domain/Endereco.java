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
@Table(name = "endereco")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable {

	private static final long serialVersionUID = -1696352685946590035L;
	
	@Id
	@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotEmpty
	@Column(length = 1000)
	private String logradouro;
	
	
	@Column(name="ponto_referencia", length = 1500)
	private String pontoReferencia;
	
	@NotEmpty
	private String cep;
	
	@OneToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
}

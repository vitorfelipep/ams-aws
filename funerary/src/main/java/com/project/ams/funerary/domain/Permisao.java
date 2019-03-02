package com.project.ams.funerary.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "permissao")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permisao implements Serializable {

	private static final long serialVersionUID = -4791444627770583479L;
	
	@Id
	@SequenceGenerator(name = "seq_permissao", sequenceName = "seq_permissao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permissao")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome_permissao")
	private String nomePermissao;

	@Column(length = 4000)
	private String descricao;
}

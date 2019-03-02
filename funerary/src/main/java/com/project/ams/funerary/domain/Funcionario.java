package com.project.ams.funerary.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "funcionario")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -352291641285050255L;

	@Id
	@SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
	@EqualsAndHashCode.Include
	private Long id;

	@Embedded
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Cargo cargo;

	@NotNull
	@Column(name="data_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name="data_desligamento")
	private LocalDateTime dataSaida;

	@Column(name="situacao_funcionario")
	private Boolean situacaoFuncionario;

	@Email(message = "Email inválido!")
	@Column(name="email_trabalho")
	private String emailAMSFuncionario;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Usuario usuario;
}

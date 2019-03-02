package com.project.ams.funerary.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.ams.funerary.domain.enumeration.TipoDependenteEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "dependente")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dependente implements Serializable {

	private static final long serialVersionUID = 1808716462194474410L;
	
	@Id
	@SequenceGenerator(name = "seq_dependente", sequenceName = "seq_dependente", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dependente")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotEmpty
	@Column(name="nome_dependente")
	private String nomeDependente;

	@NotNull
	private Boolean situacao;
	
	@NotNull
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@CPF
	@NotEmpty
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_dependente")
	private TipoDependenteEnum tipoDependente;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Parentesco parentesco;
	
	@ManyToOne
	@JoinColumn(name="titular", referencedColumnName="id")
	@JsonBackReference
	private Cliente titular;
}

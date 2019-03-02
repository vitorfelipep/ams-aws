package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.EstadoCivilEnum;
import com.project.ams.funerary.domain.enumeration.SexoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author vitor
 *
 */

@Embeddable
@Getter @Setter
public class Pessoa {

	@NotBlank
	@CPF
	private String cpf;

	@NotBlank
	private String rg;

	@NotBlank
	@Column(name="orgao_expedidor")
	private String orgaoExpedidor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	
	@NotEmpty
	@Column(name="nome_completo")
	private String nomeCompleto;
	
	@NotNull
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@NotEmpty
	@Column(name="nome_mae")
	private String nomeMae;
	
	@NotEmpty
	@Column(name="nome_pai")
	private String nomePai;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="estado_civil")
	private EstadoCivilEnum estadoCivil;
	
	@Email
	private String email;
}

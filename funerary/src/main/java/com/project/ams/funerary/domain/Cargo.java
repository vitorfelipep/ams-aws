package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.TipoFuncaoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "cargo")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cargo implements Serializable {

	private static final long serialVersionUID = 7267586993616485510L;

	@Id
	@SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cargo")
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(name = "nome_funcao")
	private String nomeFuncao;

	@Column(length = 4000)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name="tipo_funcao")
	private TipoFuncaoEnum tipoFuncao;

	@NotNull(message = "é obrigatório!")
	@Column(name = "salario_bruto", nullable = false, precision = 10, scale = 2)
	private BigDecimal salarioBruto;

	@Column(name = "salario_liquido", nullable = false, precision = 10, scale = 2)
	private BigDecimal salarioLiqido;

	@NotNull(message = "é obrigatório!")
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal commissao;
	
}

package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.TipoServicoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "servico")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servico implements Serializable {

	private static final long serialVersionUID = -1630603012715432870L;
	
	@Id
	@SequenceGenerator(name = "seq_servico", sequenceName = "seq_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servico")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotEmpty
	@Column(name="nome_servico")
	private String nome;
	
	@NotEmpty
	@Column(name="descricao_servico", length = 4000)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_servico")
	private TipoServicoEnum tipoServico;
	
	@NotNull(message = "é obrigatório!")
	@Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal precoUnitario;
}

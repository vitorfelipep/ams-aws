package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.TipoPlanoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "plano")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plano implements Serializable{

	private static final long serialVersionUID = 5587620924913102111L;
	
	@Id
	@SequenceGenerator(name = "seq_plano", sequenceName = "seq_plano", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_plano")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotEmpty
	@Column(name="nome_plano")
	private String name;
	
	@NotEmpty
	@Column(name="descricao_plano")
	private String descricao;
	
	@Column(name="data_criacao_plano")
	private LocalDateTime dataCriaçãoPlno;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_plano")
	private TipoPlanoEnum tipoPlano;
	
	@Column(name="preco") 
	private BigDecimal preco;
	
	@Column(name="desconto")
	private BigDecimal desconto;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "planos_servicos", joinColumns = @JoinColumn(name = "codigo_plano"),
			inverseJoinColumns = @JoinColumn(name = "codigo_servico"))
	private List<Servico> servicos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "planos_produtos", joinColumns = @JoinColumn(name = "codigo_plano"),
			inverseJoinColumns = @JoinColumn(name = "codigo_produto"))
	private List<Produto> produtos;
}

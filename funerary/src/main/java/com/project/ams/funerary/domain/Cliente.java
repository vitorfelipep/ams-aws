package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.TipoClienteEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7781240571041408331L;
	
	@Id
	@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_cliente")
	private TipoClienteEnum tipoCliente;
	
	@Embedded
	private Pessoa pessoa;

	@NotNull
	@Column(name="situacao_titular")
	private Boolean situacaoTitular;
	
	@Column(name="possui_cartao")
	private Boolean possuiCartao;
	
	@Column(name="sem_carencia")
	private Boolean semCarencia;

	@OneToOne(fetch = FetchType.LAZY)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "titular", cascade={ CascadeType.ALL })
	private List<Dependente> dependentes;

	@ManyToMany(fetch = FetchType.EAGER, cascade= { CascadeType.ALL })
	@JoinTable(name = "cliente_telefone", joinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_telefone", referencedColumnName = "id"))
	private List<Telefone> telefones;
}

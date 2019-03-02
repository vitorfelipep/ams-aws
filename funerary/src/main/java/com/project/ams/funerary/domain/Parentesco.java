package com.project.ams.funerary.domain;

import com.project.ams.funerary.domain.enumeration.TipoGrauParentescoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vitor
 *
 */
@Entity
@Table(name = "parentesco")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parentesco implements Serializable {

	private static final long serialVersionUID = -3555631874939989130L;

	@Id
	@SequenceGenerator(name = "seq_parentesco", sequenceName = "seq_parentesco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parentesco")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="grau_parentesco")
	private TipoGrauParentescoEnum grauParentesco;
}

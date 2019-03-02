package com.project.ams.funerary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ams.funerary.domain.enumeration.TipoProdutoEnum;
import com.project.ams.funerary.service.exception.NegocioException;
import com.project.ams.funerary.validation.SKU;
import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author vitor
 *
 */

@Entity
@Table(name = "produto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

    private static final long serialVersionUID = -2547963153486105997L;

    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "produto-1")
    @Column(nullable = false, length = 20, unique = true)
    @SKU
    @EqualsAndHashCode.Include
    private String sku;

    @NotBlank(message = "produto-2")
    @Size(max = 255)
    @Column(name="nome_produto", nullable = false)
    private String name;

    @NotBlank(message = "produto-3")
    @Size(max = 4000)
    @Column(name="descricao_produto", length = 4000)
    private String description;

    @NotNull(message = "produto-4")
    @Min(0)
    @Max(9999)
    @Column(name = "quantidade_estoque", nullable = false, length = 5)
    private Integer quantidadeEmEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_produto")
    private TipoProdutoEnum tipoProduto;

    @NotNull(message = "produto-5")
    @DecimalMin(value = "0", message = "produto-5")
    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    @JsonIgnore
    public boolean alreadyExist() {
        return getId() != null;
    }

    public void baixarEstoque(Integer quantidade) {
        int novaQuantidade = this.getQuantidadeEmEstoque() - quantidade;

        if (novaQuantidade < 0) {
            throw new NegocioException("business-error-4", HttpStatus.BAD_REQUEST);
        }

        this.setQuantidadeEmEstoque(novaQuantidade);
    }

    public void adicionarEstoque(Integer quantidade) {
        this.setQuantidadeEmEstoque(quantidadeEmEstoque + quantidade);
    }
}

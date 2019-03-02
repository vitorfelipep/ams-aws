package com.project.ams.funerary.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.project.ams.funerary.domain.Produto;
import com.project.ams.funerary.domain.enumeration.TipoProdutoEnum;
import com.project.ams.funerary.repository.Produtos;
import com.project.ams.funerary.service.exception.ExistingProductNameException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoServiceTest {

    private ProdutoService produtoService;

    @Mock
    private Produtos produtosMocked;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.produtoService = new ProdutoService(this.produtosMocked);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = ExistingProductNameException.class)
    public void should_deny_creation_of_produto_that_exist() {
        Produto produtoInDataBase = buildProductInDataBaseMock();
        when(produtosMocked.findByNameIgnoreCase("Flores de capela")).thenReturn(Optional.of(produtoInDataBase));

        Produto newProduto = Produto.builder()
                .name("Flores de capela")
                .description("Flores de capela")
                .sku("NK0011")
                .valorUnitario(new BigDecimal(700))
                .tipoProduto(TipoProdutoEnum.CAIXÃO)
                .quantidadeEmEstoque(10)
                .build();
        this.produtoService.create(newProduto);
    }

    @Test
    public void should_be_create_product() {

        Produto produtoInDataBase = buildProductInDataBaseMock();

        Produto newProduto = Produto.builder()
                .name("Flores de capela")
                .description("Flores de capela")
                .sku("NK0012")
                .valorUnitario(new BigDecimal(900))
                .tipoProduto(TipoProdutoEnum.CAIXÃO)
                .quantidadeEmEstoque(75)
                .build();
        when(produtosMocked.save(newProduto)).thenReturn(produtoInDataBase);

        assertThat(produtoInDataBase.getId(), equalTo(10L));
        assertThat(produtoInDataBase.getName(), equalTo("Flores de capela"));
        assertThat(produtoInDataBase.getTipoProduto(), equalTo(TipoProdutoEnum.CAIXÃO));
    }

    private Produto buildProductInDataBaseMock() {
        return Produto.builder()
                .id(10L)
                .name("Flores de capela")
                .description("Flores de capela")
                .sku("NK0011")
                .valorUnitario(new BigDecimal(700))
                .tipoProduto(TipoProdutoEnum.CAIXÃO)
                .quantidadeEmEstoque(10)
                .build();
    }
}
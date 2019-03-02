package com.project.ams.funerary.service;

import com.project.ams.funerary.domain.Produto;
import com.project.ams.funerary.repository.Produtos;
import com.project.ams.funerary.service.exception.ExistingProductNameException;
import com.project.ams.funerary.service.exception.NegocioException;
import com.project.ams.funerary.service.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Vitor Felipe
 */

@Service
@Transactional
public class ProdutoService implements Serializable {

    /**
     * @implNote Repository of Produto
     */

    private Produtos produtoRepository;

    public ProdutoService(@Autowired Produtos produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * @return List of Produto (List<Produto>)
     */
    public List<Produto> all() {
        return this.produtoRepository.findAll();
    }

    /**
     *
     * @param codigo
     * @return Optional<Produto>
     */
    public Optional<Produto> findOneOptional(Long codigo) { return produtoRepository.findById(codigo); }

    /**
     * @param produto
     *
     * @return Produto
     */
    public Produto create(@Valid Produto produto) {
        verifyProductExistentBySku(produto.getSku());
        produtoAlreadyExistent(produto);
        return this.produtoRepository.save(produto);
    }


    /**
     *
     * @param codigo
     * @param produto
     * @return Produto
     */
    public Produto update(Long codigo, @Valid Produto produto) {
        Produto productSaved = findOne(codigo);
        BeanUtils.copyProperties(produto, productSaved, "id");
        return produtoRepository.save(productSaved);
    }

    /**
     *
     * @param codigo
     */
    public void delete(Long codigo) {
        Optional<Produto> productOptional = findOneOptional(codigo);
        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException();
        }
        produtoRepository.deleteById(codigo);
    }

    /**
     *
     * @param codigo
     * @return Produto
     */
    private Produto findOne(Long codigo) {
        Optional<Produto> produtoExisting = produtoRepository.findById(codigo);
        return produtoExisting.orElseThrow(ProductNotFoundException::new);
    }

    /**
     *
     * @param produto
     */
    private void produtoAlreadyExistent(@Valid Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findByNameIgnoreCase(produto.getName());
        if (produtoOptional.isPresent()) {
            throw new ExistingProductNameException();
        }
    }

    /**
     *
     * @param sku
     * @return
     */
    private void verifyProductExistentBySku(String sku) {
        Optional<Produto> produtoExistente = produtoRepository.findBySkuIgnoreCase(sku);
        if (produtoExistente.isPresent()) {
            throw new NegocioException("business-error-2", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @param codigo
     * @param newAmount
     * @return
     */
    public Produto addStockInToProduct(Long codigo, @Valid Integer newAmount) {
        Produto produtoSaved = findOne(codigo);
        produtoSaved.adicionarEstoque(newAmount);
        return produtoRepository.save(produtoSaved);
    }

    /**
     *
     * @param codigo
     * @param subtractValue
     * @return
     */
    public Produto subtractStockInToProduct(Long codigo, @Valid Integer subtractValue) {
        Produto produtoSaved = findOne(codigo);
        produtoSaved.baixarEstoque(subtractValue);
        return produtoRepository.save(produtoSaved);
    }
}

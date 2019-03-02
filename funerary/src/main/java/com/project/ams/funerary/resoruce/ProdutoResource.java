package com.project.ams.funerary.resoruce;

import com.project.ams.funerary.domain.Produto;
import com.project.ams.funerary.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    private final Logger log = LoggerFactory.getLogger(ProdutoResource.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProdutoService produtoService;


    @ApiOperation(value = "View a list of available of products", httpMethod = "GET", nickname = "listAllProducts")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping
    public List<Produto> all() {
        return produtoService.all();
    }

    @ApiOperation(value = "Recovery product by id", httpMethod = "GET", nickname = "findProductById")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved item"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> findProductById(@PathVariable Long codigo) {
        Optional<Produto> produtoOptional = produtoService.findOneOptional(codigo);
        return produtoOptional.isPresent() ? ResponseEntity.ok().body(produtoOptional.get()) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Create a new product", httpMethod = "POST", nickname = "createProduct")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created Product"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto) {
        Produto produtoSaved = produtoService.create(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSaved);
    }

    @ApiOperation(value = "Update a existing product", httpMethod = "PUT", nickname = "updateProduct")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated product"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PutMapping("/{codigo}")
    public ResponseEntity<Produto> updateProduct(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
        Produto productSaved = produtoService.update(codigo, produto);
        return ResponseEntity.ok().body(productSaved);
    }

    @ApiOperation(value = "Delete a existing product", httpMethod = "PUT", nickname = "updateProduct")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated product"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @DeleteMapping("/{codigo}")
    public ResponseEntity updateProduct(@PathVariable Long codigo) {
        produtoService.delete(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Add Stock at existing product", httpMethod = "PUT", nickname = "addStockProduct")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated product"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PutMapping("/{codigo}/addStock")
    public ResponseEntity<Produto> addStockProduct(@PathVariable Long codigo, @RequestBody Integer newAmount) {
        Produto productWithNewStock = produtoService.addStockInToProduct(codigo, newAmount);
        return ResponseEntity.ok().body(productWithNewStock);
    }

    @ApiOperation(value = "Substract Stock at existing product", httpMethod = "PUT", nickname = "subTractStockProduct")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated product"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PutMapping("/{codigo}/subtractStock")
    public ResponseEntity<Produto> subTractStockProduct(@PathVariable Long codigo, @RequestBody Integer subtractValue) {
        Produto productWithNewStock = produtoService.subtractStockInToProduct(codigo, subtractValue);
        return ResponseEntity.ok().body(productWithNewStock);
    }
}

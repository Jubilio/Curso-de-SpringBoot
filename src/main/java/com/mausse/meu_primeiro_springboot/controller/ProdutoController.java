package com.mausse.meu_primeiro_springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mausse.meu_primeiro_springboot.model.Produto;
import com.mausse.meu_primeiro_springboot.service.ProdutoService;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
   
    @GetMapping
    public List<Produto> listaProdutos() {
        return produtoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
       
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
       
    }

    @PostMapping
    public Produto criaProduto(@RequestBody Produto produto) {
        
        return produtoService.salvarProduto(produto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }


    
}

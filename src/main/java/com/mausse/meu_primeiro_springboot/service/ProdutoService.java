package com.mausse.meu_primeiro_springboot.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mausse.meu_primeiro_springboot.model.Produto;
import com.mausse.meu_primeiro_springboot.repository.ProdutoRepository;
import com.mausse.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Aqui você pode adicionar métodos para manipular os produtos

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id+ "."));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {

        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id + ".");
        }
         
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            return produtoRepository.save(produto);
        }
        return null; // ou lançar uma exceção, dependendo do seu caso de uso
    }
}

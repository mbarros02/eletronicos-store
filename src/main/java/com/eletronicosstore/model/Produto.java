package com.eletronicosstore.model;

import java.io.Serializable;
import java.util.List;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private double avaliacao;
    private String descricao;
    private double preco;
    private int qtdEstoque;
    private List<ImagemProduto> imagens;
    private int status;

    public Produto() {}

    public Produto(double avaliacao, String descricao, int id, List<ImagemProduto> imagens, String nome, double preco, int qtdEstoque) {
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.id = id;
        this.imagens = imagens;
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProduto> imagens) {
        this.imagens = imagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public boolean isAtivo() { return status == 1; }
    
    public void setAtivo(boolean ativo) { this.status = ativo ? 1 : 0; }

    public ImagemProduto getImagemPrincipal() {
        if (imagens == null) return null;
        return imagens.stream()
                .filter(ImagemProduto::isPrincipal)
                .findFirst()
                .orElse(null);
    }
}


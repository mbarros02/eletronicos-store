package com.eletronicosstore.models;

import java.io.Serializable;

public class ItemCarrinho implements Serializable {
    private Produto produto;
    private int quantidade;
    
    public ItemCarrinho() {}
    
    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
    
    public void incrementarQuantidade() {
        this.quantidade++;
    }
    
    public void decrementarQuantidade() {
        if (this.quantidade > 1) {
            this.quantidade--;
        }
    }
}

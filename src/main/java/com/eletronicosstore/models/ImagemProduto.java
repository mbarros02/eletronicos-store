package com.eletronicosstore.model;

public class ImagemProduto {
    private int id;
    private String caminho;
    private boolean principal;
    private int idProduto;

    public ImagemProduto() {}

    public ImagemProduto(String caminho, int id, int idProduto, boolean principal) {
        this.caminho = caminho;
        this.id = id;
        this.idProduto = idProduto;
        this.principal = principal;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
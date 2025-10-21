package com.eletronicosstore.model;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha1;
    private String senha2;
    private boolean status;
    private int idGrupo;
    private String nomeGrupo;

    public Usuario() {}

    public Usuario(int id, String nome, String cpf, String email, String senha1, int idGrupo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha1 = senha1;
        this.idGrupo = idGrupo;
    }

    public Usuario(int id, String nome, String cpf, String email, String senha1, String senha2, int idGrupo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha1 = senha1;
        this.senha2 = senha2;
        this.idGrupo = idGrupo;
    }

    public Usuario(String cpf, String email, int id, int idGrupo, String nome, String nomeGrupo, String senha1, String senha2, boolean status) {
        this.cpf = cpf;
        this.email = email;
        this.id = id;
        this.idGrupo = idGrupo;
        this.nome = nome;
        this.nomeGrupo = nomeGrupo;
        this.senha1 = senha1;
        this.senha2 = senha2;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }
}

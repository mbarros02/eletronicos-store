package com.eletronicosstore.models;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha1;
    private String senha2;
    private boolean status;
    private int idGrupo;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String email, String senha1, String senha2, int idGrupo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha1 = senha1;
        this.senha2 = senha2;
        this.idGrupo = idGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
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

    public void setSenha1(String senha) {
        this.senha1 = senha;
    }

    public boolean getStatus() {
        return status;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void mudarStatus() {
        if (this.getStatus()) {
            this.setStatus(false);
        } else {
            this.setStatus(false);
        }
    }

    public void mudarStatus (int status) {
        if (status == 1) {
            this.setStatus(true);
        } else {
            this.setStatus(false);
        }
    }
}

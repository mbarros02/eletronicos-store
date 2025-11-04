package com.eletronicosstore.models;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private String email;
    private String cpf;
    private String senha1;
    private String senha2;

    public Cliente() {}

    public Cliente(int id, String nome, String cpf, String sexo, LocalDate dataNasc, String email, String senha1) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNasc;
        this.email = email;
        this.senha1 = senha1;
    }

    public Cliente(int id, String nome, String sexo, LocalDate dataNascimento, String email, String cpf, String senha1, String senha2) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.cpf = cpf;
        this.senha1 = senha1;
        this.senha2 = senha2;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}

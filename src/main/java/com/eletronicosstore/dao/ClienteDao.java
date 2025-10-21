package com.eletronicosstore.dao;

import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao implements Base<Cliente>{

    @Override
    public Cliente cadastrar(Cliente cliente) {

        String sql = "INSERT INTO clientes (NOME, CPF, SEXO, DATA_NASC, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getSexo());
            stmt.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getSenha1());

            stmt.execute();
            System.out.println("Cliente cadastrado com  sucesso!");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar Cliente!");
        }
        return null;
    }

    @Override
    public Cliente alterar(Cliente input) {
        return null;
    }

    @Override
    public Cliente buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Cliente> listarTodos(String filtro) {
        return List.of();
    }

    @Override
    public Cliente alterarStatus(Cliente input) {
        return null;
    }
}

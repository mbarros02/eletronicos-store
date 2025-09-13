package com.eletronicosstore.dao;

import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.Produto;

import java.sql.*;
import java.util.List;

public class ProdutoDao implements Base<Produto>{

    @Override
    public Produto cadastrar(Produto input) {
        String sql = "INSERT INTO produtos (NOME, AVALIACAO, DESCRICAO, PRECO, QTD_ESTOQUE) VALUES (?, ?, ?, ?, ?);";


        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, input.getNome());
            stmt.setDouble(2, input.getAvaliacao());
            stmt.setString(3, input.getDescricao());
            stmt.setDouble(4, input.getPreco());
            stmt.setInt(5, input.getQtdEstoque());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                input.setId(rs.getInt(1));
            }

            rs.close();
            stmt.close();
            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    @Override
    public Produto alterar(Produto input) {
        return null;
    }

    @Override
    public Produto buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Produto> listarTodos(String filtro) {
        return List.of();
    }
}


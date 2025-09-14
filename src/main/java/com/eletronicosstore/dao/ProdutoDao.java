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

        String sql = "UPDATE produtos SET nome=?, avaliacao=?, descricao=?, preco=?, qtd_estoque=? WHERE idproduto=?;";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input.getNome());
            stmt.setDouble(2, input.getAvaliacao());
            stmt.setString(3, input.getDescricao());
            stmt.setDouble(4, input.getPreco());
            stmt.setInt(5, input.getQtdEstoque());
            stmt.setInt(6, input.getId());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Produto alterado com sucesso!!");

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return input;
    }

    @Override
    public Produto buscarPorId(int id) {

        String sql = "SELECT * FROM produtos WHERE idproduto=?;";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    rs.getInt("id");
                    rs.getString("nome");
                    rs.getDouble("avaliacao");
                    rs.getString("descricao");
                    rs.getDouble("preco");
                    rs.getInt("qtd_estoque");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos(String filtro) {
        return List.of();
    }
}


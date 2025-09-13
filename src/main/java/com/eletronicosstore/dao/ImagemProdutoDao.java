package com.eletronicosstore.dao;

import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.ImagemProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImagemProdutoDao implements Base<ImagemProduto>{

    @Override
    public ImagemProduto cadastrar(ImagemProduto input) {
        String sql = "INSERT INTO imagem_produto (CAMINHO, PRINCIPAL, ID_PRODUTO) VALUES (?, ?, ?);";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, input.getCaminho());
            stmt.setBoolean(2, input.isPrincipal());
            stmt.setInt(3, input.getIdProduto());

            stmt.executeUpdate();

            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                input.setId(rs.getInt(1));
            }

            rs.close();
            stmt.close();
            System.out.println("Imagem cadastrada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    @Override
    public ImagemProduto alterar(ImagemProduto input) {
        return null;
    }

    @Override
    public ImagemProduto buscarPorId(int id) {
        return null;
    }

    @Override
    public List<ImagemProduto> listarTodos(String filtro) {
        return List.of();
    }
}

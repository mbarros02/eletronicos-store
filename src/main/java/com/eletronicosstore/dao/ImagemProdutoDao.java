package com.eletronicosstore.dao;

import com.eletronicosstore.models.ImagemProduto;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String sql = "UPDATE imagem_Produto SET caminho=?, principal=? WHERE idimagem=?;";

        try(Connection conn = new Conexao().getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input.getCaminho());
            stmt.setBoolean(2, input.isPrincipal());
            stmt.setInt(3, input.getIdProduto());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Imagem alterada com sucesso!!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    @Override
    public ImagemProduto buscarPorId(int id) {
        return null;
    }

    @Override
    public List<ImagemProduto> listarTodos(String filtro) {
        return List.of();
    }

    @Override
    public ImagemProduto alterarStatus(ImagemProduto input) {
        return null;
    }

    public List<ImagemProduto> listarPorProdutoId(int idProduto) {
        List<ImagemProduto> imagens = new java.util.ArrayList<>();
        String sql = "SELECT * FROM imagem_produto WHERE id_produto = ?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ImagemProduto imagem = new ImagemProduto();
                imagem.setId(rs.getInt("idimagem"));
                imagem.setCaminho(rs.getString("caminho"));
                imagem.setPrincipal(rs.getBoolean("principal"));
                imagem.setIdProduto(rs.getInt("id_produto"));
                imagens.add(imagem);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imagens;
    }

    public void removerPorProdutoId(int idProduto) {
        String sql = "DELETE FROM imagem_produto WHERE id_produto = ?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

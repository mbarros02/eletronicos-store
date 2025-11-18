package com.eletronicosstore.dao;

import com.eletronicosstore.models.ItemPedido;
import com.eletronicosstore.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemPedidoDao implements Base<ItemPedido> {

    @Override
    public ItemPedido cadastrar(ItemPedido input) {
        String sql = "INSERT INTO itens_pedido (idpedido, idproduto, quantidade, preco_unitario, subtotal) " +
                "VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, input.getIdPedido());
            stmt.setInt(2, input.getIdProduto());
            stmt.setInt(3, input.getQuantidade());
            stmt.setDouble(4, input.getPrecoUnitario());
            stmt.setDouble(5, input.getSubtotal());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                input.setIdItemPedido(rs.getInt(1));
            }

            rs.close();
            stmt.close();
            System.out.println("Item de pedido cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar item do pedido: " + e.getMessage(), e);
        }
        return input;
    }

    @Override
    public ItemPedido alterar(ItemPedido input) {
        return null;
    }

    @Override
    public ItemPedido alterarStatus(ItemPedido input) {
        return null;
    }

    @Override
    public ItemPedido buscarPorId(int id) {
        String sql = "SELECT * FROM itens_pedido WHERE iditem=?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdItemPedido(rs.getInt("iditem"));
                item.setIdPedido(rs.getInt("idpedido"));
                item.setIdProduto(rs.getInt("idproduto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                item.setSubtotal(rs.getDouble("subtotal"));
                return item;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item do pedido por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<ItemPedido> listarTodos(String filtro) {
        return null;
    }

    public List<ItemPedido> listarPorPedido(int idPedido) {
        List<ItemPedido> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_pedido WHERE idpedido=?";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdItemPedido(rs.getInt("iditem"));
                item.setIdPedido(rs.getInt("idpedido"));
                item.setIdProduto(rs.getInt("idproduto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                item.setSubtotal(rs.getDouble("subtotal"));
                itens.add(item);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens do pedido: " + e.getMessage(), e);
        }

        return itens;
    }

    public List<ItemPedido> buscarItensPorPedido(int idPedido) {
        List<ItemPedido> itens = new ArrayList<>();
        String sql = "SELECT ip.*, p.NOME AS nome_produto " +
                "FROM itens_pedido ip " +
                "INNER JOIN produtos p ON ip.idproduto = p.IDPRODUTO " +
                "WHERE ip.idpedido = ?";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdItemPedido(rs.getInt("iditem"));
                item.setIdPedido(rs.getInt("idpedido"));
                item.setIdProduto(rs.getInt("idproduto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                item.setSubtotal(rs.getDouble("subtotal"));

                item.setNomeProduto(rs.getString("nome_produto"));

                itens.add(item);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens do pedido com nome do produto: " + e.getMessage(), e);
        }

        return itens;
    }
}

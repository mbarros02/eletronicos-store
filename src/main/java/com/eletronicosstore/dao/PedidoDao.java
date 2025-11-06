package com.eletronicosstore.dao;

import com.eletronicosstore.models.Pedido;
import com.eletronicosstore.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao implements Base<Pedido> {
    @Override
    public Pedido cadastrar(Pedido input) {
        String sql = "INSERT INTO pedidos (idcliente, endereco_entrega, forma_pagamento, nome_cartao, parcelas, subtotal, frete, total, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, input.getIdCliente());
            stmt.setString(2, input.getEnderecoEntrega());
            stmt.setString(3, input.getFormaPagamento());
            stmt.setString(4, input.getNomeCartao());
            stmt.setString(5, input.getParcelas());
            stmt.setDouble(6, input.getSubtotal());
            stmt.setDouble(7, input.getFrete());
            stmt.setDouble(8, input.getTotal());
            stmt.setString(9, input.getStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar o pedido, nenhuma linha afetada.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    input.setIdPedido(idGerado);
                    input.setNumeroPedido(idGerado);

                    String sqlUpdate = "UPDATE pedidos SET numero_pedido=? WHERE idpedido=?";
                    try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                        stmtUpdate.setInt(1, idGerado);
                        stmtUpdate.setInt(2, idGerado);
                        stmtUpdate.executeUpdate();
                    }
                } else {
                    throw new SQLException("Falha ao obter o ID gerado do pedido.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar pedido", e);
        }
        return input;
    }

    @Override
    public Pedido alterar(Pedido input) {
        return null;
    }

    @Override
    public Pedido alterarStatus(Pedido input) {
        String sql = "UPDATE pedidos SET status=? WHERE idpedido=?;";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, input.getStatus());
            stmt.setInt(2, input.getIdPedido());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do pedido: " + e.getMessage(), e);
        }
        return input;
    }

    @Override
    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE idpedido=?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idpedido"));
                pedido.setNumeroPedido(rs.getInt("numero_pedido"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setEnderecoEntrega(rs.getString("endereco_entrega"));
                pedido.setFormaPagamento(rs.getString("forma_pagamento"));
                pedido.setNomeCartao(rs.getString("nome_cartao"));
                pedido.setParcelas(rs.getString("parcelas"));
                pedido.setSubtotal(rs.getDouble("subtotal"));
                pedido.setFrete(rs.getDouble("frete"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setStatus(rs.getString("status"));
                pedido.setData(rs.getTimestamp("data_pedido"));
                return pedido;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Pedido> listarTodos(String filtro) {
        List<Pedido> pedidos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM pedidos");
        boolean hasFilter = filtro != null && !filtro.isBlank();
        if (hasFilter) sql.append(" WHERE idcliente = ? OR numero_pedido = ?");
        sql.append(" ORDER BY idpedido DESC");

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            if (hasFilter) {
                int filtroInt = Integer.parseInt(filtro);
                stmt.setInt(1, filtroInt);
                stmt.setInt(2, filtroInt);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idpedido"));
                pedido.setNumeroPedido(rs.getInt("numero_pedido"));
                pedido.setIdCliente(rs.getInt("idcliente"));
                pedido.setEnderecoEntrega(rs.getString("endereco_entrega"));
                pedido.setFormaPagamento(rs.getString("forma_pagamento"));
                pedido.setNomeCartao(rs.getString("nome_cartao"));
                pedido.setParcelas(rs.getString("parcelas"));
                pedido.setSubtotal(rs.getDouble("subtotal"));
                pedido.setFrete(rs.getDouble("frete"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        }
        return pedidos;
    }

    public List<Pedido> listarPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE idcliente = ? ORDER BY data_pedido DESC";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("idpedido"));
                p.setNumeroPedido(rs.getInt("numero_pedido"));
                p.setTotal(rs.getDouble("total"));
                p.setStatus(rs.getString("status"));
                p.setData(rs.getTimestamp("data_pedido"));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }


}

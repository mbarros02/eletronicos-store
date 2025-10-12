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

    public void atualizarStatus(int id, boolean ativo) {
        String sql = "UPDATE produtos SET status=? WHERE idproduto=?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ativo ? 1 : 0);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto alterarStatus(Produto input) {
        String sql = "UPDATE produtos SET status=? WHERE idproduto=?;";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, input.getStatus());
            stmt.setInt(2, input.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    @Override
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE idproduto=?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Produto produto = new Produto();
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setAvaliacao(rs.getDouble("avaliacao"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                try { produto.setStatus(rs.getInt("status")); } catch(Exception ignored) {}
                return produto;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos(String filtro) {
        return listarTodos(filtro, 0, 10);
    }

    public List<Produto> listarTodos(String filtro, int offset, int limit) {
        List<Produto> produtos = new java.util.ArrayList<>();
        boolean hasFilter = filtro != null && !filtro.isBlank();
        Integer filtroId = null;
        if (hasFilter) {
            try { filtroId = Integer.valueOf(filtro.trim()); } catch (Exception ignored) {}
        }
        StringBuilder sql = new StringBuilder("SELECT * FROM produtos");
        if (hasFilter) {
            if (filtroId != null) {
                sql.append(" WHERE idproduto = ?");
            } else {
                sql.append(" WHERE nome LIKE ?");
            }
        }
        sql.append(" ORDER BY idproduto DESC LIMIT ? OFFSET ?");
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            int idx = 1;
            if (hasFilter) {
                if (filtroId != null) {
                    stmt.setInt(idx++, filtroId);
                } else {
                    stmt.setString(idx++, "%" + filtro + "%");
                }
            }
            stmt.setInt(idx++, limit);
            stmt.setInt(idx, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setAvaliacao(rs.getDouble("avaliacao"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                produto.setStatus(rs.getInt("status"));
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public int contarProdutos(String filtro) {
        boolean hasFilter = filtro != null && !filtro.isBlank();
        Integer filtroId = null;
        if (hasFilter) {
            try { filtroId = Integer.valueOf(filtro.trim()); } catch (Exception ignored) {}
        }
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM produtos");
        if (hasFilter) {
            if (filtroId != null) {
                sql.append(" WHERE idproduto = ?");
            } else {
                sql.append(" WHERE nome LIKE ?");
            }
        }
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            int idx = 1;
            if (hasFilter) {
                if (filtroId != null) {
                    stmt.setInt(idx++, filtroId);
                } else {
                    stmt.setString(idx++, "%" + filtro + "%");
                }
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void ativarTodos() {
        String sql = "UPDATE produtos SET status=1";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarEstoque(int id, int estoque) {
        String sql = "UPDATE produtos SET qtd_estoque=? WHERE idproduto=?;";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, estoque);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listarPorId(int id) {
        List<Produto> produtos = new java.util.ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE idproduto = ?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setAvaliacao(rs.getDouble("avaliacao"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                produto.setStatus(rs.getInt("status"));
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public List<Produto> listarAtivos() {
        List<Produto> produtos = new java.util.ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 1 ORDER BY idproduto DESC";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setAvaliacao(rs.getDouble("avaliacao"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                produto.setStatus(rs.getInt("status"));
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
}


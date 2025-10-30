package com.eletronicosstore.dao;

import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.util.Conexao;

import java.sql.*;
import java.util.List;

public class ClienteDao implements Base<Cliente> {

    @Override
    public Cliente cadastrar(Cliente cliente) {

        String sql = "INSERT INTO clientes (NOME, CPF, SEXO, DATA_NASC, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getSexo());
            stmt.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getSenha1());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
                System.out.println("ID gerado: " + cliente.getId());
            }

            System.out.println("Cliente cadastrado com sucesso!");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar Cliente! " + e.getMessage());
        }

        return cliente;
    }

    @Override
    public Cliente alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome=?, data_nasc=?, sexo=?, senha=? WHERE idcliente=?";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getSenha1());
            stmt.setInt(5, cliente.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
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

    public Cliente buscarPorEmail(String email) {

        String sql = "SELECT * FROM CLIENTES WHERE EMAIL=?";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("IDCLIENTE"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("SEXO"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                return cliente;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

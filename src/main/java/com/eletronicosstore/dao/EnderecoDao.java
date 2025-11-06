package com.eletronicosstore.dao;

import com.eletronicosstore.models.EnderecoCliente;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao implements Base<EnderecoCliente>{

    @Override
    public EnderecoCliente cadastrar(EnderecoCliente endereco) {

        String sql = "INSERT INTO enderecos_clientes (CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, LOCALIDADE, UF, TIPO_ENDERECO, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getLocalidade());
            stmt.setString(7, endereco.getUf());
            stmt.setString(8, endereco.getTipoEndereco());
            stmt.setInt(9, endereco.getIdCliente());

            stmt.execute();
            System.out.println("Cadastro de endereço realizado com sucesso!");
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar um endereço!", e);
        }
        return null;
    }

    @Override
    public EnderecoCliente alterarStatus(EnderecoCliente input) {
        return null;
    }

    public int contarEnderecos(int idCliente) {
        int quantidade = 0;
        String sql = "SELECT IDENDERECO FROM ENDERECOS_CLIENTES WHERE ID_CLIENTE = ?";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                quantidade = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantidade;
    }

    public List<EnderecoCliente> listarPorCliente(int idCliente) {
        String sql = "SELECT * FROM enderecos_clientes WHERE ID_CLIENTE = ?";
        List<EnderecoCliente> enderecos = new ArrayList<>();

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EnderecoCliente e = new EnderecoCliente();
                e.setId(rs.getInt("IDENDERECO"));
                e.setCep(rs.getString("CEP"));
                e.setLogradouro(rs.getString("LOGRADOURO"));
                e.setComplemento(rs.getString("COMPLEMENTO"));
                e.setBairro(rs.getString("BAIRRO"));
                e.setLocalidade(rs.getString("LOCALIDADE"));
                e.setUf(rs.getString("UF"));
                e.setTipoEndereco(rs.getString("TIPO_ENDERECO"));
                e.setIdCliente(rs.getInt("ID_CLIENTE"));
                e.setStatus(rs.getInt("STATUS"));
                enderecos.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    public void inativar(int id) {
        String sql = "UPDATE enderecos_clientes SET STATUS = 0 WHERE IDENDERECO = ?";
        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reativar(int id) {
        String sql = "UPDATE enderecos_clientes SET STATUS = 1 WHERE IDENDERECO = ?";
        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public EnderecoCliente alterar(EnderecoCliente input) {
        return null;
    }

    @Override
    public EnderecoCliente buscarPorId(int id) {
        String sql = "SELECT * FROM enderecos_clientes WHERE idendereco = ?";
        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EnderecoCliente endereco = new EnderecoCliente();
                endereco.setId(rs.getInt("idendereco"));
                endereco.setCep(rs.getString("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setUf(rs.getString("uf"));
                endereco.setTipoEndereco(rs.getString("tipo_endereco"));
                endereco.setStatus(rs.getInt("status"));
                endereco.setIdCliente(rs.getInt("id_cliente"));
                return endereco;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<EnderecoCliente> listarTodos(String filtro) {
        return List.of();
    }

}

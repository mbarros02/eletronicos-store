package com.eletronicosstore.dao;

import com.eletronicosstore.models.EnderecoCliente;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDao implements Base<EnderecoCliente>{

    @Override
    public EnderecoCliente cadastrar(EnderecoCliente endereco) {

        String sql = "INSERT INTO enderecos_clientes (CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, LOCALIDADE, UF, TIPO_ENDERECO, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getLocalidade());
            stmt.setString(6, endereco.getUf());
            stmt.setString(7, endereco.getTipoEndereco());
            stmt.setInt(8, endereco.getIdCliente());

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
    public EnderecoCliente alterar(EnderecoCliente input) {
        return null;
    }

    @Override
    public EnderecoCliente buscarPorId(int id) {
        return null;
    }

    @Override
    public List<EnderecoCliente> listarTodos(String filtro) {
        return List.of();
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

    public void alterarTipoEndereco(int idEndereco, String novoTipo, int idCliente) throws Exception {
        Connection conn = new Conexao().getConnection();

        try {
            if (novoTipo.contains("F")) {
                String verificaSql = "SELECT IDENDERECO FROM enderecos_clientes WHERE ID_CLIENTE = ? AND TIPO_ENDERECO LIKE '%F%' AND IDENDERECO <> ?";
                PreparedStatement verificaStmt = conn.prepareStatement(verificaSql);
                verificaStmt.setInt(1, idCliente);
                verificaStmt.setInt(2, idEndereco);
                ResultSet rs = verificaStmt.executeQuery();

                if (rs.next()) {
                    int idAnterior = rs.getInt("IDENDERECO");
                    String removeF = "UPDATE enderecos_clientes SET TIPO_ENDERECO = REPLACE(TIPO_ENDERECO, 'F', '') WHERE IDENDERECO = ?";
                    PreparedStatement updateAnterior = conn.prepareStatement(removeF);
                    updateAnterior.setInt(1, idAnterior);
                    updateAnterior.executeUpdate();
                }
            }

            String sql = "UPDATE enderecos_clientes SET TIPO_ENDERECO = ? WHERE IDENDERECO = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, novoTipo);
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {
            conn.close();
            throw e;
        }
    }

}

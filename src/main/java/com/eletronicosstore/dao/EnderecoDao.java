package com.eletronicosstore.dao;

import com.eletronicosstore.dto.EnderecoDto;
import com.eletronicosstore.models.EnderecoCliente;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EnderecoDao implements Base<EnderecoCliente>{

    @Override
    public EnderecoCliente cadastrar(EnderecoCliente endereco) {

        String sql = "INSERT INTO enderecos_clientes (CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, LOCALIDADE, UF, ID_CLIENTE, TIPO_ENDERECO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getLocalidade());
            stmt.setString(6, endereco.getUf());
            stmt.setInt(7, endereco.getIdCliente());
            stmt.setString(8, endereco.getTipoEndereco());

            stmt.execute();
            System.out.println("Cadastro de endereço realizado com sucesso!");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar um endereço!");
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
}

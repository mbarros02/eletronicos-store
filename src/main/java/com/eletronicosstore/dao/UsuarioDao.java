package com.eletronicosstore.dao;

import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao implements Base<Usuario> {

    @Override
    public Usuario cadastrar(Usuario input) {

        String sql = "INSERT INTO USUARIOS (NOME, CPF, EMAIL, SENHA, ID_GRUPO_USUARIO) VALUES (?, ?, ?, ?, ?);";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input.getNome());
            stmt.setString(2, input.getCpf());
            stmt.setString(3, input.getEmail());
            stmt.setString(4, input.getSenha());
            stmt.setInt(5, input.getIdGrupo());

            stmt.execute();
            System.out.println("Usuário Cadastrado com Sucesso!");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

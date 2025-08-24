package com.eletronicosstore.dao;

import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao implements Base<Usuario> {

    @Override
    public Usuario cadastrar(Usuario input) {

        String sql = "INSERT INTO USUARIOS (NOME, SOBRENOME, EMAIL, SENHA) VALUES (?, ?, ?, ?);";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input.getNome());
            stmt.setString(2, input.getSobrenome());
            stmt.setString(3, input.getEmail());
            stmt.setString(4, input.getSenha());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

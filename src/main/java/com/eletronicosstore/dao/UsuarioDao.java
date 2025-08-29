package com.eletronicosstore.dao;

import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            stmt.setString(4, input.getSenha1());
            stmt.setInt(5, input.getIdGrupo());

            stmt.execute();
            System.out.println("Usuário Cadastrado com Sucesso!");
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Usuario alterar(Usuario input) {

        String sql = "UPDATE usuarios SET nome=?, cpf=?, senha=?, id_grupo_usuario=? WHERE idUsuario=?;";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, input.getNome());
            stmt.setString(2, input.getCpf());
            stmt.setString(3, input.getSenha1());
            stmt.setInt(4, input.getIdGrupo());
            stmt.setInt(5, input.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Usuario buscarPorEmail(String email) {

        String sql = "SELECT * FROM USUARIOS WHERE EMAIL=?";

        try (Connection conn = new Conexao().getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha1"),
                        rs.getInt("idGrupo")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

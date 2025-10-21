package com.eletronicosstore.dao;

import com.eletronicosstore.model.Usuario;
import com.eletronicosstore.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return input;
    }

    @Override
    public Usuario alterarStatus(Usuario input) {

        String sql = "UPDATE usuarios SET status=? WHERE idUsuario=?;";

        try(Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, input.isStatus() ? 1 : 0);
            stmt.setInt(2, input.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    @Override
    public Usuario buscarPorId(int id) {

        String sql = "SELECT * FROM usuarios WHERE idUsuario=?";

        try (Connection conn = new Conexao().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("id_grupo_usuario")
                );
                usuario.setStatus(rs.getInt("status") == 1);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos(String filtro) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT " +
                "usuarios.IDUSUARIO, " +
                "usuarios.NOME, " +
                "usuarios.CPF, " +
                "usuarios.EMAIL, " +
                "usuarios.SENHA, " +
                "usuarios.STATUS, " +
                "usuarios.ID_GRUPO_USUARIO, " +
                "grupo_usuario.NOME AS nomeGrupo " +
                "FROM usuarios " +
                "INNER JOIN grupo_usuario ON usuarios.ID_GRUPO_USUARIO = grupo_usuario.IDGRUPO " +
                "WHERE usuarios.NOME LIKE ?";

        try (Connection conn = new Conexao().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + (filtro != null ? filtro : "") + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("id_grupo_usuario")
                );
                usuario.setStatus(rs.getInt("status") == 1);
                usuario.setNomeGrupo(rs.getString("nomeGrupo"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }


    public Usuario buscarPorEmail(String email) {

        String sql = "SELECT * FROM USUARIOS WHERE EMAIL=?";

        try (Connection conn = new Conexao().getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("IDUSUARIO"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA"),
                        rs.getInt("ID_GRUPO_USUARIO")
                );
                usuario.setStatus(rs.getInt("STATUS") == 1);
                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.database.HashUtils;
import com.eletronicosstore.models.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cadastro")
public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.cadastrar(req, resp);
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        }

    }

    public void cadastrar (HttpServletRequest req, HttpServletResponse resp) throws ServletException, ClassNotFoundException, IOException {
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        int idGrupo = Integer.parseInt(req.getParameter("idGrupo"));

        String hashSenha = HashUtils.hashSenha(senha);

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(hashSenha);
        usuario.setIdGrupo(idGrupo);

        UsuarioDao dao = new UsuarioDao();
        dao.cadastrar(usuario);

        resp.sendRedirect("list-usuario.jsp");
    }
}

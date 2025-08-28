package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.database.HashUtils;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.utils.ValidarCpf;

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

    private void cadastrar (HttpServletRequest req, HttpServletResponse resp) throws ServletException, ClassNotFoundException, IOException {
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String senha1 = req.getParameter("senha1");
        String senha2 = req.getParameter("senha2");
        int idGrupo = Integer.parseInt(req.getParameter("idGrupo"));

        try {
            if(this.ChecarValorNulo(nome, cpf, email, senha1, senha2)) {
                req.setAttribute("erro", "Campos vazios!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
            if (!ValidarCpf.valido(cpf)) {
                req.setAttribute("erro", "CPF inválido!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return;
            }
            if(!senha1.equals(senha2)) {
                throw new IllegalArgumentException("As senhas são diferentes!!");
            }
            String hashSenha = HashUtils.hashSenha(senha1);

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setSenha1(hashSenha);
            usuario.setIdGrupo(idGrupo);

            UsuarioDao dao = new UsuarioDao();
            dao.cadastrar(usuario);

            resp.sendRedirect("list-usuario.jsp");

        } catch (IOException exception) {
            throw new ServletException(exception);
        }
    }
    private boolean ChecarValorNulo(String... valores) {
        for (String c : valores) {
            if(c == null || c.isBlank()) {
                return true;
            }
        }
        return false;
    }
}

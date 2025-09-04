package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.models.UsuarioAtual;
import com.eletronicosstore.utils.ValidarSenha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.eletronicosstore.models.Usuario;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String status = req.getParameter("status");

        if (this.checarValorNulo(email, senha)) {
            req.setAttribute("erro", "Email e senha são obrigatórios!");
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario != null && ValidarSenha.verificarSenha(senha,usuario.getSenha1())){
            if (!usuario.isStatus()){
                req.setAttribute("erro", "Usuário inativo!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return;
        }

            HttpSession session = req.getSession();
            session.setAttribute("usuarioAtual", usuario);

            int idGrupo = usuario.getIdGrupo();
            int idUsuario = usuario.getId();

            if (idGrupo == 1) {
                resp.sendRedirect("usuario?action=listar&id=" + idUsuario);
            } else if (idGrupo == 2) {
                resp.sendRedirect("usuario?action=listarEstoquista&id=" + idUsuario);
            } else {
                resp.sendRedirect("erro.jsp");
            }
        } else {
            req.setAttribute("erro", "Email ou senha inválidos!");
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
        }
    }

    private boolean checarValorNulo(String... valores) {
        for (String c : valores) {
            if (c == null || c.isBlank()) {
                return true;
            }
        }
        return false;
    }
}

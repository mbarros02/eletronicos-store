package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.service.ValidarSenha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.eletronicosstore.service.ChecarNulo.checarValorNulo;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (checarValorNulo(email, senha)) {
            req.setAttribute("erro", "Email e senha são obrigatórios!");
            req.getRequestDispatcher("/WEB-INF/views/usuario/login.jsp").forward(req, resp);
            return;
        }

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario != null && ValidarSenha.verificarSenha(senha,usuario.getSenha1())){
            if (!usuario.isStatus()){
                req.setAttribute("erro", "Usuário inativo!");
                req.getRequestDispatcher("/WEB-INF/views/usuario/login.jsp").forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("usuarioAtual", usuario);

            int idGrupo = usuario.getIdGrupo();
            int idUsuario = usuario.getId();

            if (idGrupo == 1) {
                resp.sendRedirect("usuario?action=listar&id=" + idUsuario);
            } else if (idGrupo == 2) {
                resp.sendRedirect("produto?action=listar");
            } else {
                resp.sendRedirect("/WEB-INF/views/usuario/login.jsp");
            }
        } else {
            req.setAttribute("erro", "Email ou senha inválidos!");
            req.getRequestDispatcher("/WEB-INF/views/usuario/login.jsp").forward(req, resp);
        }
    }
}

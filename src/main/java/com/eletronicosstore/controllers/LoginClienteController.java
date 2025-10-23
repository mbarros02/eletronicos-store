package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.service.ValidarSenha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eletronicosstore.service.ChecarNulo.checarValorNulo;

@WebServlet("/login-cliente")
public class LoginClienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (checarValorNulo(email, senha)) {
            req.setAttribute("erro", "Email e senha são obrigatórios!");
            req.getRequestDispatcher("/WEB-INF/views/cliente/login-cliente.jsp").forward(req, resp);
            return;
        }

        ClienteDao dao = new ClienteDao();
        Cliente cliente = dao.buscarPorEmail(email);

        if (cliente != null && ValidarSenha.verificarSenha(senha, cliente.getSenha1())) {
            req.getRequestDispatcher("/WEB-INF/views/cliente/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", "Email ou senha inválidos!");
            req.getRequestDispatcher("/WEB-INF/views/usuario/login.jsp").forward(req, resp);
        }
    }

}

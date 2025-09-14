package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.UsuarioDao;
import com.eletronicosstore.utils.ValidarSenha;
import com.eletronicosstore.models.Usuario;
import com.eletronicosstore.utils.ValidarCpf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("cadastro".equals(action)) {
                this.cadastrar(req, resp);
            } else if ("alterar".equals(action)) {
                this.alterar(req, resp);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "listar";
        }
        try {
            if ("listar".equals(action)) {
                listar(req, resp);
            } else if ("trocarStatus".equals(action)) {
                trocarStatus(req, resp);
            } else if ("incluir".equals(action)) {
                req.getRequestDispatcher("cad-usuario.jsp").forward(req, resp);
            } else if ("alterarForm".equals(action)) {
                alterarForm(req, resp);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, ClassNotFoundException, IOException {
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String senha1 = req.getParameter("senha1");
        String senha2 = req.getParameter("senha2");
        int idGrupo = Integer.parseInt(req.getParameter("idGrupo"));

        try {
            if (this.ChecarValorNulo(nome, cpf, email, senha1, senha2)) {
                req.setAttribute("erro", "Campos vazios!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
            if (!ValidarCpf.valido(cpf)) {
                req.setAttribute("erro", "CPF inválido!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return;
            }
            if (!senha1.equals(senha2)) {
                throw new IllegalArgumentException("As senhas são diferentes!!");
            }
            String hashSenha = ValidarSenha.hashSenha(senha1);

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setSenha1(hashSenha);
            usuario.setIdGrupo(idGrupo);

            UsuarioDao dao = new UsuarioDao();
            dao.cadastrar(usuario);

            resp.sendRedirect("usuario?action=listar");

        } catch (IOException exception) {
            throw new ServletException(exception);
        }
    }

    public void alterar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, ClassNotFoundException {
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String senha1 = req.getParameter("senha1");
        String senha2 = req.getParameter("senha2");
        int idGrupo = Integer.parseInt(req.getParameter("idGrupo"));
        int id = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioAtual");

        try {
            if (this.ChecarValorNulo(nome, cpf, senha1, senha2)) {
                req.setAttribute("erro", "Campos vazios!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
            if (!ValidarCpf.valido(cpf)) {
                req.setAttribute("erro", "CPF inválido!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return;
            }
            if (!senha1.equals(senha2)) {
                throw new IllegalArgumentException("As senhas são diferentes!!");
            }
            String hashSenha = ValidarSenha.hashSenha(senha1);

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setSenha1(hashSenha);
            if (usuarioLogado.getId() == id) {
                usuario.setIdGrupo(usuarioLogado.getIdGrupo());
            } else {
                usuario.setIdGrupo(idGrupo);
            }
            usuario.setId(id);

            UsuarioDao dao = new UsuarioDao();
            dao.alterar(usuario);

            resp.sendRedirect("usuario?action=listar");

        } catch (IOException exception) {
            throw new ServletException(exception);
        }
    }

    private boolean ChecarValorNulo(String... valores) {
        for (String c : valores) {
            if (c == null || c.isBlank()) {
                return true;
            }
        }
        return false;
    }


    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        String filtroNome = req.getParameter("filtroNome");
        UsuarioDao dao = new UsuarioDao();
        if (filtroNome == null) {
            filtroNome = "";
        }
        List<Usuario> usuarios = dao.listarTodos(filtroNome);

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("filtroNome", filtroNome);
        req.getRequestDispatcher("painel-administrador.jsp").forward(req, resp);
    }

    private void trocarStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(req.getParameter("id"));

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscarPorId(id);

        if (usuario != null) {
            usuario.setStatus(!usuario.getStatus());
            dao.alterarStatus(usuario);
        }
        resp.sendRedirect("usuario?action=listar");
    }

    private void alterarForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(req.getParameter("id"));

        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscarPorId(id);

        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("alt-usuario.jsp").forward(req, resp);
    }
}

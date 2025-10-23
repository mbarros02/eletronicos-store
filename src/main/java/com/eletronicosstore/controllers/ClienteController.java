package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.ClienteDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.service.ChecarNulo;
import com.eletronicosstore.service.ValidarSenha;
import com.eletronicosstore.util.ValidarCpf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static com.eletronicosstore.service.ChecarNulo.checarValorNulo;

@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("cadastro".equals(action)) {
                this.cadastrar(request, response);
            } else if ("alterar".equals(action)) {
                this.alterar(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("alterar".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/cliente/alt-cliente.jsp").forward(req, resp);
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String dataNasc = request.getParameter("dataNasc");
        String email = request.getParameter("email");
        String senha1 = request.getParameter("senha1");
        String senha2 = request.getParameter("senha2");

        LocalDate dataNascimento = LocalDate.parse(dataNasc);
        System.out.println("Data recebida: " + dataNascimento);

        if (dataNascimento == null) {
            throw new IllegalArgumentException("Campo de data vazio.");
        }

        try {
            if (ChecarNulo.checarValorNulo(nome, cpf, email, senha1)) {
                request.setAttribute("Campos: Nome, cpf, email, senha", "Esses campos não podem ser vazios!");
                request.getRequestDispatcher("/WEB-INF/views/cliente/login-cliente.jsp").forward(request, response);
            }
            if (!ValidarCpf.valido(cpf)) {
                request.setAttribute("CPF Inválido!", "Erro!");
                request.getRequestDispatcher("/WEB-INF/views/cliente/login-cliente.jsp").forward(request, response);
            }
            if (!senha1.equals(senha2)) {
                throw new IllegalArgumentException("As senhas são diferentes!!");
            }
            String hashSenha = ValidarSenha.hashSenha(senha1);

            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setSexo(sexo);
            cliente.setDataNascimento(dataNascimento);
            cliente.setEmail(email);
            cliente.setSenha1(hashSenha);

            ClienteDao dao = new ClienteDao();
            dao.cadastrar(cliente);

            request.getSession().setAttribute("cliente", cliente);
            response.sendRedirect(request.getContextPath() + "/endereco?action=cadastro");

        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    public void alterar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, ClassNotFoundException {
        String nome = req.getParameter("nome");
        String dataNasc = req.getParameter("dataNasc");
        String sexo = req.getParameter("sexo");
        String senha1 = req.getParameter("senha1");
        String senha2 = req.getParameter("senha2");

        LocalDate dataNascimento = LocalDate.parse(dataNasc);
        System.out.println("Data recebida: " + dataNascimento);

        if (dataNascimento == null) {
            throw new IllegalArgumentException("Campo de data vazio.");
        }

        try {
            if (checarValorNulo(nome, dataNasc, sexo, senha1, senha2)) {
                req.setAttribute("erro", "Campos vazios!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
            if (!senha1.equals(senha2)) {
                throw new IllegalArgumentException("As senhas são diferentes!!");
            }
            String hashSenha = ValidarSenha.hashSenha(senha1);

            Cliente cliente = new Cliente();
            cliente.setId(((Cliente) req.getSession().getAttribute("cliente")).getId());
            cliente.setNome(nome);
            cliente.setDataNascimento(dataNascimento);
            cliente.setSexo(sexo);
            cliente.setSenha1(hashSenha);

            ClienteDao dao = new ClienteDao();
            dao.alterar(cliente);

            req.getRequestDispatcher("/WEB-INF/views/cliente/menu.jsp").forward(req, resp);

        } catch (IOException exception) {
            throw new ServletException(exception);
        }
    }
}

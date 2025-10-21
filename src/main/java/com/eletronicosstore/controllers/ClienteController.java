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

@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        try {
            if ("cadastro".equals(action)) {
                this.cadastrar(request, response);
            }
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String dataNasc = request.getParameter("dataNascimento");
        String email = request.getParameter("email");
        String senha1 = request.getParameter("senha1");
        String senha2 = request.getParameter("senha2");

        LocalDate dataNascimento = LocalDate.parse(dataNasc);

        try {
            if(ChecarNulo.checarValorNulo(nome, cpf, email, senha1)) {
                request.setAttribute("Campos: Nome, cpf, email, senha", "Esses campos não podem ser vazios!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            if(!ValidarCpf.valido(cpf)) {
                request.setAttribute("Erro", "CPF inválido!");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
                return;
            }
            if(!senha1.equals(senha2)) {
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

            response.sendRedirect("cliente?id=" + cliente.getId());

        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

}

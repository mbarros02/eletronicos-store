package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.EnderecoDao;
import com.eletronicosstore.models.EnderecoCliente;
import com.eletronicosstore.service.ViaCep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/endereco")
public class EnderecoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("cadastro".equals(action)) {
                this.cadastrar(request, response);
            } else if ("definirPrincipal".equals(action)) {
                int idEndereco = Integer.parseInt(request.getParameter("id"));

                EnderecoDao enderecoDao = new EnderecoDao();
                enderecoDao.desmarcarTodosEntrega();
                enderecoDao.marcarComoPrincipal(idEndereco);
            }
        } catch (IOException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("cadastro".equals(action)) {
            String idClienteParam = request.getParameter("id_cliente");
            int id_cliente = 0;

            if (idClienteParam != null && !idClienteParam.isEmpty()) {
                id_cliente = Integer.parseInt(idClienteParam);
            }

            EnderecoDao dao = new EnderecoDao();
            int totalEnderecos = dao.contarEnderecos(id_cliente);
            request.setAttribute("totalEnderecos", totalEnderecos);

            request.getRequestDispatcher("/WEB-INF/views/cliente/cad-endereco.jsp").forward(request, response);
        } else if ("listar".equals(action)) {
            int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
            EnderecoDao dao = new EnderecoDao();
            request.setAttribute("enderecos", dao.listarPorCliente(idCliente));
            request.getRequestDispatcher("/WEB-INF/views/cliente/list-endereco.jsp").forward(request, response);
        } else if ("inativar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
            EnderecoDao dao = new EnderecoDao();
            dao.inativar(id);
            response.sendRedirect("endereco?action=listar&id_cliente=" + idCliente);
        } else if ("reativar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
            EnderecoDao dao = new EnderecoDao();
            dao.reativar(id);
            response.sendRedirect("endereco?action=listar&id_cliente=" + idCliente);
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ViaCep viaCep = new ViaCep();

        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String localidade = request.getParameter("localidade");
        String uf = request.getParameter("uf");
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        String tipo_endereco = request.getParameter("tipo_endereco");

        try {
            EnderecoCliente endereco = viaCep.buscarEnderecoPorCep(cep);

            endereco.setNumero(request.getParameter("numero"));
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setTipoEndereco(request.getParameter("tipo_endereco"));
            endereco.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));

            EnderecoDao dao = new EnderecoDao();
            dao.cadastrar(endereco);

            request.getSession().setAttribute("enderecos", endereco);
            response.sendRedirect(request.getContextPath() + "/endereco?action=cadastro&id_cliente=" + id_cliente);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

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
            } else if ("alterar".equals(action)) {
                this.atualizarTipo(request, response);
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
        }
    }


    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ViaCep viaCep = new ViaCep();

        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String localidade = request.getParameter("localidade");
        String uf = request.getParameter("uf");
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        String tipo_endereco = request.getParameter("tipo_endereco");

        try {
            EnderecoCliente endereco = viaCep.buscarEnderecoPorCep(cep);

            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setTipoEndereco(request.getParameter("tipo_endereco"));
            endereco.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));

            EnderecoDao dao = new EnderecoDao();
            dao.cadastrar(endereco);

            response.sendRedirect(request.getContextPath() + "/menu?action=cadastro&id_cliente=" + id_cliente);
            return;
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void atualizarTipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEndereco = Integer.parseInt(request.getParameter("id_endereco"));
        String tipoEndereco = request.getParameter("tipo_endereco");
        int idCliente = Integer.parseInt(request.getParameter("id_cliente"));

        EnderecoDao dao = new EnderecoDao();
        try {
            dao.alterarTipoEndereco(idEndereco, tipoEndereco, idCliente);
            response.sendRedirect("listar-enderecos.jsp?id_cliente=" + idCliente);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

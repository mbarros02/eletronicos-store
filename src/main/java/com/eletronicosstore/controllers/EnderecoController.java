package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.EnderecoDao;
import com.eletronicosstore.dto.EnderecoDto;
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
        ViaCep viaCep = new ViaCep();

        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String complemento = request.getParameter("Complemento");
        String bairro = request.getParameter("bairro");
        String localidade = request.getParameter("localidade");
        String uf = request.getParameter("uf");
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        String tipo_endereco = request.getParameter("tipo_endereco");

        try {

            try {
                EnderecoDto endereco = viaCep.buscarEnderecoPorCep(cep);
                request.setAttribute("endereco", endereco);
            } catch (Exception e) {
                request.setAttribute("erro", "Erro ao buscar o CEP: " + e.getMessage());
            }

            EnderecoCliente endereco = new EnderecoCliente();
            endereco.setCep(cep);
            endereco.setLogradouro(logradouro);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);
            endereco.setLocalidade(localidade);
            endereco.setUf(uf);
            endereco.setIdCliente(id_cliente);
            endereco.setTipoEndereco(tipo_endereco);


            EnderecoDao enderecoDao = new EnderecoDao();
            enderecoDao.cadastrar(endereco);

            response.sendRedirect("index.jsp");

        } catch (IOException e) {
            throw new ServletException(e);
        }
    }
}

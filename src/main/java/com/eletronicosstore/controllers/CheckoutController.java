package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.EnderecoDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.models.EnderecoCliente;
import com.eletronicosstore.models.ItemCarrinho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
        Double frete = (Double) session.getAttribute("freteSelecionado");

        if (carrinho == null || carrinho.isEmpty()) {
            resp.sendRedirect("carrinho");
            return;
        }

        double subtotal = carrinho.stream().mapToDouble(ItemCarrinho::getSubtotal).sum();
        double total = subtotal + (frete != null ? frete : 0.00);

        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            resp.sendRedirect("login");
            return;
        }

        EnderecoDao enderecoDao = new EnderecoDao();
        List<EnderecoCliente> enderecos = enderecoDao.listarPorCliente(cliente.getId());

        req.setAttribute("carrinho", carrinho);
        req.setAttribute("subtotal", subtotal);
        req.setAttribute("frete", frete != null ? frete : 0.00);
        req.setAttribute("total", total);
        req.setAttribute("enderecos", enderecos);

        req.getRequestDispatcher("/WEB-INF/views/cliente/checkout.jsp").forward(req, resp);
    }
}


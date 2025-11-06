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

        EnderecoDao enderecoDao = new EnderecoDao();
        List<EnderecoCliente> enderecos = enderecoDao.listarPorCliente(cliente.getId());

        req.setAttribute("carrinho", carrinho);
        req.setAttribute("subtotal", subtotal);
        req.setAttribute("frete", frete != null ? frete : 0.00);
        req.setAttribute("total", total);
        req.setAttribute("enderecos", enderecos);

        req.getRequestDispatcher("/WEB-INF/views/cliente/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
        Double frete = (Double) session.getAttribute("freteSelecionado");
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (carrinho == null || carrinho.isEmpty()) {
            resp.sendRedirect("carrinho");
            return;
        }

        double subtotal = carrinho.stream().mapToDouble(ItemCarrinho::getSubtotal).sum();
        double total = subtotal + (frete != null ? frete : 0.00);

        String enderecoSelecionado = req.getParameter("enderecoSelecionado");
        String formaPagamento = req.getParameter("formaPagamento");
        String numeroCartao = req.getParameter("numeroCartao");
        String nomeCartao = req.getParameter("nomeCartao");
        String codigoVerificador = req.getParameter("codigoVerificador");
        String dataVencimento = req.getParameter("dataVencimento");
        String parcelas = req.getParameter("parcelas");

        EnderecoDao enderecoDao = new EnderecoDao();
        EnderecoCliente endereco = enderecoDao.buscarPorId(Integer.parseInt(enderecoSelecionado));

        req.setAttribute("carrinho", carrinho);
        req.setAttribute("subtotal", subtotal);
        req.setAttribute("frete", frete != null ? frete : 0.00);
        req.setAttribute("total", total);
        req.setAttribute("endereco", endereco);
        req.setAttribute("formaPagamento", formaPagamento);
        req.setAttribute("numeroCartao", numeroCartao);
        req.setAttribute("nomeCartao", nomeCartao);
        req.setAttribute("codigoVerificador", codigoVerificador);
        req.setAttribute("dataVencimento", dataVencimento);
        req.setAttribute("parcelas", parcelas);

        req.getRequestDispatcher("/WEB-INF/views/cliente/resumo.jsp").forward(req, resp);
    }
}


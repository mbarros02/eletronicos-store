package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.ProdutoDao;
import com.eletronicosstore.models.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carrinho")
public class CarrinhoController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }

        String idStr = req.getParameter("idProduto");
        if (idStr != null) {
            int idProduto = Integer.parseInt(idStr);
            Produto produto = new ProdutoDao().buscarPorId(idProduto);
            if (produto != null) {
                carrinho.add(produto);
            }
        }

        session.setAttribute("carrinho", carrinho);
        resp.sendRedirect("carrinho.jsp");
    }
}

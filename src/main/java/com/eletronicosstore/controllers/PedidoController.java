package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.EnderecoDao;
import com.eletronicosstore.dao.ItemPedidoDao;
import com.eletronicosstore.dao.PedidoDao;
import com.eletronicosstore.models.Cliente;
import com.eletronicosstore.models.ItemCarrinho;
import com.eletronicosstore.models.ItemPedido;
import com.eletronicosstore.models.Pedido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pedido")
public class PedidoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");

        if (carrinho == null || carrinho.isEmpty()) {
            resp.sendRedirect("carrinho.jsp");
            return;
        }

        try {
            String enderecoEntrega = req.getParameter("endereco");
            String formaPagamento = req.getParameter("formaPagamento");
            String nomeCartao = req.getParameter("nomeCartao");
            String parcelas = req.getParameter("parcelas");
            double subtotal = Double.parseDouble(req.getParameter("subtotal"));
            double frete = Double.parseDouble(req.getParameter("frete"));
            double total = Double.parseDouble(req.getParameter("total"));

            Pedido pedido = new Pedido();
            pedido.setIdCliente(cliente.getId());
            pedido.setEnderecoEntrega(enderecoEntrega);
            pedido.setFormaPagamento(formaPagamento);
            pedido.setNomeCartao(nomeCartao);
            pedido.setParcelas(parcelas);
            pedido.setSubtotal(subtotal);
            pedido.setFrete(frete);
            pedido.setTotal(total);
            pedido.setStatus("Aguardando pagamento");

            PedidoDao pedidoDao = new PedidoDao();
            ItemPedidoDao itemDao = new ItemPedidoDao();

            Pedido pedidoGravado = pedidoDao.cadastrar(pedido);

            if (pedidoGravado != null && pedidoGravado.getIdPedido() > 0) {
                List<ItemPedido> itensPedido = new ArrayList<>();
                for (ItemCarrinho ic : carrinho) {
                    ItemPedido ip = new ItemPedido();
                    ip.setIdProduto(ic.getProduto().getId());
                    ip.setQuantidade(ic.getQuantidade());
                    ip.setPrecoUnitario(ic.getProduto().getPreco());
                    ip.setIdPedido(pedidoGravado.getIdPedido());
                    itensPedido.add(ip);
                }

                for (ItemPedido item : itensPedido) {
                    itemDao.cadastrar(item);
                }

                session.setAttribute("statusPedido", "sucesso");
                session.setAttribute("numeroPedido", pedidoGravado.getNumeroPedido());
                session.setAttribute("valorTotal", pedidoGravado.getTotal());

                session.removeAttribute("carrinho");
                session.removeAttribute("subtotal");
                session.removeAttribute("frete");
                session.removeAttribute("total");

            } else {
                session.setAttribute("statusPedido", "erro");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("statusPedido", "erro");
        }

        req.getRequestDispatcher("/WEB-INF/views/cliente/home.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String idClienteParam = req.getParameter("id_cliente");

        PedidoDao dao = new PedidoDao();

        if (idParam != null) {
            int idPedido = Integer.parseInt(idParam);
            Pedido pedido = dao.buscarPorId(idPedido);
            req.setAttribute("pedido", pedido);
            req.getRequestDispatcher("/WEB-INF/views/cliente/detalhes-pedido.jsp").forward(req, resp);
            return;
        }

        if (idClienteParam != null) {
            int idCliente = Integer.parseInt(idClienteParam);
            List<Pedido> pedidos = dao.listarPorCliente(idCliente);
            req.setAttribute("pedidos", pedidos);
            req.getRequestDispatcher("/WEB-INF/views/cliente/meus-pedidos.jsp").forward(req, resp);
        }
    }
}

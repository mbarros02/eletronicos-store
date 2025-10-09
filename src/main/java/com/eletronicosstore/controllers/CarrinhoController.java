package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.ProdutoDao;
import com.eletronicosstore.models.Produto;
import com.eletronicosstore.models.ItemCarrinho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carrinho")
public class CarrinhoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
        
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        
        // Calcular totais
        double subtotal = calcularSubtotal(carrinho);
        double frete = calcularFrete(req, carrinho);
        double total = subtotal + frete;
        
        System.out.println("DEBUG: Subtotal: " + subtotal + ", Frete: " + frete + ", Total: " + total);
        
        req.setAttribute("carrinho", carrinho);
        req.setAttribute("subtotal", subtotal);
        req.setAttribute("frete", frete);
        req.setAttribute("total", total);
        
        req.getRequestDispatcher("Carrinho.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        System.out.println("DEBUG: Action recebida: " + action);

        @SuppressWarnings("unchecked")
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
            System.out.println("DEBUG: Carrinho inicializado como nova lista");
        } else {
            System.out.println("DEBUG: Carrinho existente com " + carrinho.size() + " itens");
        }
        
        if ("adicionar".equals(action)) {
            System.out.println("DEBUG: Executando adicionar produto");
            adicionarProduto(req, carrinho);
        } else if ("aumentar".equals(action)) {
            aumentarQuantidade(req, carrinho);
        } else if ("diminuir".equals(action)) {
            diminuirQuantidade(req, carrinho);
        } else if ("remover".equals(action)) {
            removerProduto(req, carrinho);
        } else if ("calcularFrete".equals(action)) {
            System.out.println("DEBUG: Calculando frete");
            // O frete será calculado e salvo na sessão
            String freteSelecionado = req.getParameter("frete");
            if (freteSelecionado != null) {
                double frete = 0.00;
                switch (freteSelecionado) {
                    case "economico":
                        frete = 15.00;
                        break;
                    case "normal":
                        frete = 25.00;
                        break;
                    case "expresso":
                        frete = 35.00;
                        break;
                    default:
                        frete = 0.00;
                }
                session.setAttribute("freteSelecionado", frete);
                System.out.println("DEBUG: Frete salvo na sessão: " + frete);
            }
        }
        
        System.out.println("DEBUG: Salvando carrinho na sessão com " + carrinho.size() + " itens");
        session.setAttribute("carrinho", carrinho);
        resp.sendRedirect("carrinho");
    }
    
    private void adicionarProduto(HttpServletRequest req, List<ItemCarrinho> carrinho) {
        String idStr = req.getParameter("idProduto");
        System.out.println("DEBUG: idProduto recebido: " + idStr);
        
        if (idStr != null) {
            try {
                int idProduto = Integer.parseInt(idStr);
                Produto produto = new ProdutoDao().buscarPorId(idProduto);
                System.out.println("DEBUG: Produto encontrado: " + (produto != null ? produto.getNome() : "null"));
                
                if (produto != null) {
                    // Verificar se produto já existe no carrinho
                    boolean produtoExiste = false;
                    for (ItemCarrinho item : carrinho) {
                        if (item.getProduto().getId() == produto.getId()) {
                            item.incrementarQuantidade();
                            produtoExiste = true;
                            System.out.println("DEBUG: Produto já existe, incrementando quantidade");
                            break;
                        }
                    }
                    
                    if (!produtoExiste) {
                        carrinho.add(new ItemCarrinho(produto, 1));
                        System.out.println("DEBUG: Produto adicionado ao carrinho");
                    }
                    
                    System.out.println("DEBUG: Tamanho do carrinho: " + carrinho.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("DEBUG: Erro ao converter idProduto: " + e.getMessage());
            }
        }
    }
    
    private void aumentarQuantidade(HttpServletRequest req, List<ItemCarrinho> carrinho) {
        String idStr = req.getParameter("idProduto");
        if (idStr != null) {
            int idProduto = Integer.parseInt(idStr);
            for (ItemCarrinho item : carrinho) {
                if (item.getProduto().getId() == idProduto) {
                    item.incrementarQuantidade();
                    break;
                }
            }
        }
    }
    
    private void diminuirQuantidade(HttpServletRequest req, List<ItemCarrinho> carrinho) {
        String idStr = req.getParameter("idProduto");
        if (idStr != null) {
            int idProduto = Integer.parseInt(idStr);
            for (ItemCarrinho item : carrinho) {
                if (item.getProduto().getId() == idProduto) {
                    item.decrementarQuantidade();
                    break;
                }
            }
        }
    }
    
    private void removerProduto(HttpServletRequest req, List<ItemCarrinho> carrinho) {
        String idStr = req.getParameter("idProduto");
        if (idStr != null) {
            int idProduto = Integer.parseInt(idStr);
            carrinho.removeIf(item -> item.getProduto().getId() == idProduto);
        }
    }
    
    private double calcularSubtotal(List<ItemCarrinho> carrinho) {
        return carrinho.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
    }
    
    private double calcularFrete(HttpServletRequest req, List<ItemCarrinho> carrinho) {
        HttpSession session = req.getSession();
        
        // Verificar se há frete salvo na sessão
        Double freteSalvo = (Double) session.getAttribute("freteSelecionado");
        System.out.println("DEBUG: Frete salvo na sessão: " + freteSalvo);
        
        // Retornar frete salvo na sessão ou 0 se não houver
        double resultado = freteSalvo != null ? freteSalvo : 0.00;
        System.out.println("DEBUG: Frete final: " + resultado);
        return resultado;
    }
}

package com.eletronicosstore.controllers;

import com.eletronicosstore.dao.ProdutoDao;
import com.eletronicosstore.dao.ImagemProdutoDao;
import com.eletronicosstore.models.Produto;
import com.eletronicosstore.models.ImagemProduto;
import com.eletronicosstore.database.Conexao;
import com.eletronicosstore.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@WebServlet("/produto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ProdutoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("cadastrar".equals(action)) {
                this.cadastrar(req, resp);
            } else if ("alterar".equals(action)) {
                this.alterar(req, resp);
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.equals("listar")) {
            this.listarProdutos(req, resp);

        } else if (action.equals("listarPublico")) {
            this.listarProdutosPublico(req, resp);

        } else if (action.equals("incluir")) {
            req.getRequestDispatcher("Sistema/cad-produto.jsp").forward(req, resp);

        } else if (action.equals("alterarForm")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Produto produto = new ProdutoDao().buscarPorId(id);
                req.setAttribute("produto", produto);
                req.getRequestDispatcher("Sistema/alt-produto.jsp").forward(req, resp);
            }

        } else if (action.equals("inativar") || action.equals("reativar")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Produto produto = new Produto();
                produto.setId(id);
                produto.setStatus(action.equals("reativar") ? 1 : 0);
                new ProdutoDao().alterarStatus(produto);
                resp.sendRedirect("produto?action=listar");
            }

        } else if (action.equals("visualizar")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                ProdutoDao produtoDao = new ProdutoDao();
                Produto produto = produtoDao.buscarPorId(id);
                if (produto != null) {
                    ImagemProdutoDao imagemDao = new ImagemProdutoDao();
                    List<ImagemProduto> imagens = imagemDao.listarPorProdutoId(id);
                    produto.setImagens(imagens);
                }
                req.setAttribute("produto", produto);
                req.getRequestDispatcher("Sistema/visualizar-produto.jsp").forward(req, resp);
            }
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, ClassNotFoundException, IOException {

        String nome = req.getParameter("nome");
        String avaliacaoStr = req.getParameter("avaliacao");
        String descricao = req.getParameter("descricao");
        String precoStr = req.getParameter("preco");
        String estoqueStr = req.getParameter("estoque");
        String imagemPrincipal = req.getParameter("imagemPrincipal");

        try {
            if (this.ChecarValorNulo(nome, avaliacaoStr, descricao, precoStr, estoqueStr)) {
                req.setAttribute("erro", "Campos obrigatórios não preenchidos!");
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return;
            }

            double avaliacao = Double.parseDouble(avaliacaoStr);
            double preco = Double.parseDouble(precoStr);
            int estoque = Integer.parseInt(estoqueStr);

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setAvaliacao(avaliacao);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setQtdEstoque(estoque);

            Connection conn = new Conexao().getConnection();
            ProdutoDao produtoDao = new ProdutoDao();
            produto = produtoDao.cadastrar(produto);

            ImagemProdutoDao imagemDao = new ImagemProdutoDao();
            Collection<Part> parts = req.getParts();
            List<ImagemProduto> imagens = new ArrayList<>();

            for (Part part : parts) {
                if (part.getName().equals("imagens") && part.getSize() > 0) {
                    String nomeOriginal = Paths.get(getFileName(part)).getFileName().toString();
                    String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

                    String caminhoReal = getServletContext().getRealPath("/imagens");
                    File diretorio = new File(caminhoReal);
                    if (!diretorio.exists()) diretorio.mkdirs();

                    String caminhoFinal = caminhoReal + File.separator + novoNome;
                    part.write(caminhoFinal);

                    ImagemProduto imagem = new ImagemProduto();
                    imagem.setCaminho("imagens/" + novoNome);
                    imagem.setPrincipal(nomeOriginal.equals(imagemPrincipal));
                    imagem.setIdProduto(produto.getId());

                    imagemDao.cadastrar(imagem);
                    imagens.add(imagem);
                }
            }
            resp.sendRedirect(req.getContextPath() + "/produto?action=listar");

        } catch (IOException | NumberFormatException exception) {
            throw new ServletException(exception);
        }
    }

    private void alterar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, ClassNotFoundException, IOException {
        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioAtual");
        if (usuarioLogado == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        if (usuarioLogado.getIdGrupo() == 1) {
            String nome = req.getParameter("nome");
            String avaliacaoStr = req.getParameter("avaliacao");
            String descricao = req.getParameter("descricao");
            String precoStr = req.getParameter("preco");
            String estoqueStr = req.getParameter("estoque");
            String imagemPrincipal = req.getParameter("imagemPrincipal");
            String idStr = req.getParameter("idproduto");

            try {
                double avaliacao = Double.parseDouble(avaliacaoStr);
                double preco = Double.parseDouble(precoStr);
                int estoque = Integer.parseInt(estoqueStr);
                int idproduto = Integer.parseInt(idStr);

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setAvaliacao(avaliacao);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQtdEstoque(estoque);
                produto.setId(idproduto);

                ProdutoDao produtoDao = new ProdutoDao();
                produtoDao.alterar(produto);

                ImagemProdutoDao imagemDao = new ImagemProdutoDao();
                
                Collection<Part> parts = req.getParts();
                List<ImagemProduto> imagens = new ArrayList<>();

                for (Part part : parts) {
                    if (part.getName().equals("imagens") && part.getSize() > 0) {
                        String nomeOriginal = Paths.get(getFileName(part)).getFileName().toString();
                        String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

                        String caminhoReal = getServletContext().getRealPath("/imagens");
                        File diretorio = new File(caminhoReal);
                        if (!diretorio.exists()) diretorio.mkdirs();

                        String caminhoFinal = caminhoReal + File.separator + novoNome;
                        part.write(caminhoFinal);

                        ImagemProduto imagem = new ImagemProduto();
                        imagem.setCaminho("imagens/" + novoNome);
                        imagem.setPrincipal(nomeOriginal.equals(imagemPrincipal));
                        imagem.setIdProduto(produto.getId());

                        imagemDao.cadastrar(imagem);
                        imagens.add(imagem);
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/produto?action=listar");

            } catch (IOException | NumberFormatException exception) {
                throw new ServletException(exception);
            }
        } else if (usuarioLogado.getIdGrupo() == 2) {
            String estoqueStr = req.getParameter("estoque");
            String idStr = req.getParameter("idproduto");
            try {
                int estoque = Integer.parseInt(estoqueStr);
                int idproduto = Integer.parseInt(idStr);
                ProdutoDao produtoDao = new ProdutoDao();
                produtoDao.atualizarEstoque(idproduto, estoque);
                resp.sendRedirect(req.getContextPath() + "/produto?action=listar");
            } catch (IOException | NumberFormatException exception) {
                throw new ServletException(exception);
            }
        }
    }

    private void listarProdutos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filtroNome = req.getParameter("filtroNome");
        String filtroId = req.getParameter("filtroId");
        String paginaStr = req.getParameter("pagina");
        int pagina = 1;
        int limite = 10;
        if (paginaStr != null) {
            try {
                pagina = Integer.parseInt(paginaStr);
            } catch (NumberFormatException ignored) {}
        }
        int offset = (pagina - 1) * limite;
        ProdutoDao produtoDao = new ProdutoDao();
        // Se ambos filtros estiverem preenchidos, prioriza filtroId e limpa filtroNome
        if (filtroId != null && !filtroId.isBlank() && filtroNome != null && !filtroNome.isBlank()) {
            filtroNome = "";
        }
        String filtroEfetivo = (filtroId != null && !filtroId.isBlank()) ? filtroId : filtroNome;
        List<Produto> produtos;
        int totalProdutos;
        int totalPaginas;
        try {
            if (filtroId != null && !filtroId.isBlank()) {
                int idFiltro = Integer.parseInt(filtroId.trim());
                produtos = produtoDao.listarPorId(idFiltro);
                totalProdutos = produtos.size();
                totalPaginas = 1;
                req.setAttribute("filtroNome", "");
                req.setAttribute("filtroId", filtroId);
            } else {
                produtos = produtoDao.listarTodos(filtroNome, offset, limite);
                totalProdutos = produtoDao.contarProdutos(filtroNome);
                totalPaginas = (int) Math.ceil((double) totalProdutos / limite);
            }
        } catch (NumberFormatException ex) {
            produtos = produtoDao.listarTodos(filtroNome, offset, limite);
            totalProdutos = produtoDao.contarProdutos(filtroNome);
            totalPaginas = (int) Math.ceil((double) totalProdutos / limite);
        }
        req.setAttribute("produtos", produtos);
        req.setAttribute("pagina", Integer.valueOf(pagina));
        req.setAttribute("totalPaginas", Integer.valueOf(totalPaginas));
        req.setAttribute("totalProdutos", Integer.valueOf(totalProdutos));
        req.setAttribute("filtroNome", filtroNome);
        req.setAttribute("filtroId", filtroId);
        req.getRequestDispatcher("Sistema/list-produto.jsp").forward(req, resp);
    }

    private void listarProdutosPublico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> produtos = produtoDao.listarAtivos();
        req.setAttribute("produtos", produtos);
        req.getRequestDispatcher("list-produto-publico.jsp").forward(req, resp);
    }

    private boolean ChecarValorNulo(String... valores) {
        for (String c : valores) {
            if (c == null || c.isBlank()) {
                return true;
            }
        }
        return false;
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String cd : contentDisp.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}

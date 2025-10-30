<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.eletronicosstore.dao.ProdutoDao" %>
<%@ page import="com.eletronicosstore.models.Produto" %>
<%@ page import="java.util.List" %>
<%
    ProdutoDao produtoDao = new ProdutoDao();
    List<Produto> produtos = produtoDao.listarAtivos();
    request.setAttribute("produtos", produtos);
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@12/swiper-bundle.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.png">
    <title>TechStore</title>
</head>
<body>
    <%@ include file="/assets/components/header.jsp" %>

    <section id="pincipal">
        <div>
            <h1>Eletronicos</h1>
        </div>
    </section>

    <section id="Produtos">
        <div class="col">
            <div class="title">
                <h1>Produtos Gerais</h1>
            </div>
        </div>
        <div class="content">
            <c:forEach var="produto" items="${produtos}">
                <div class="card">
                    <div class="img">
                        <img src="${pageContext.request.contextPath}/${produto.imagens[0].caminho}">
                    </div>
                    <div class="content-card">
                        <div class="title-card">
                            <h3>${produto.nome}</h3>
                        </div>
                        <div class="description">
                            <p>${produto.descricao}</p>
                            <div>R$ <fmt:formatNumber value="${produto.preco}" type="currency" currencySymbol="R$" /></div>
                        </div>
                        <div class="buton">
                            <a href="${pageContext.request.contextPath}/produto?action=visualizar&id=${produto.id}">
                                <button>Ver detalhes</button>
                            </a>
                            <form action="${pageContext.request.contextPath}/carrinho" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="adicionar" />
                                <input type="hidden" name="idProduto" value="${produto.id}" />
                                <button type="submit">Adicionar ao Carrinho</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

    <section id="Produtos">
        <div class="col">
            <div class="title">
                <h1>Produtos em Destaque</h1>
            </div>
        </div>
        <div class="content">
            <c:forEach var="produto" items="${produtos}">
                <div class="card">
                    <div class="img">
                        <img src="${pageContext.request.contextPath}/${produto.imagens[0].caminho}">
                    </div>
                    <div class="content-card">
                        <div class="title-card">
                            <h3>${produto.nome}</h3>
                        </div>
                        <div class="description">
                            <p>${produto.descricao}</p>
                            <div>R$ <fmt:formatNumber value="${produto.preco}" type="currency" currencySymbol="R$" /></div>
                        </div>
                        <div class="buton">
                            <a href="${pageContext.request.contextPath}/produto?action=visualizar&id=${produto.id}">
                                <button>Ver detalhes</button>
                            </a>
                            <form action="${pageContext.request.contextPath}/carrinho" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="adicionar" />
                                <input type="hidden" name="idProduto" value="${produto.id}" />
                                <button type="submit">Adicionar ao Carrinho</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

    <section id="Noticias">
        <div class="title">
            <h1>Receba as Últimas Novidades e Ofertas!</h1>
        </div>
        <div class="description">
            <h2>Inscreva-se em nossa newsletter para descontos exclusivos e lançamentos.</h2>
        </div>
        <div class="enviar">
            <input type="text" placeholder="Seu e-mail">
            <button>Inscrever</button>
        </div>
    </section>

    <%@ include file="/assets/components/footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/swiper@12/swiper-bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>

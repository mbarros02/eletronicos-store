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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">

    <!-- Remover link stylesheet abaixo ⬇ (colocado de forma provisória) Ass: Nicolas  -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <title>TechStore</title>
</head>
<body>
    <!-- Remover header (colocado de forma provisória) Depois o Murilo se vira 😁 Ass: Nicolas -->
    <header>
            <a href="/" class="Logo">
                <div class="img-logo">
                    <h1 class="Logo">TechStore</h1>
                </div>
            </a>
            <div class="links">
                <div>
                    <button id="btnLogin" class="Login">Olá, ${cliente.nome}</button>
                </div>
                <div id="opcoesLogin" class="opcoes">
                    <a href="${pageContext.request.contextPath}/cliente?action=alterar" class="Login">Alterar Dados</a></br>
                    <a href="${pageContext.request.contextPath}/endereco?action=listar" class="Login">Alterar Endereço</a></br>
                    <a href="/index.jsp" class="Login" style="color: red;">Sair</a>
                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/produto?action=listarPublico" class="Produtos">Produtos</a>
                </div>
                <div>
                    <a href="\" class="Categoria">Categoria</a>
                </div>
                <div>
                    <a href="\" class="Sobre">Sobre Nós</a>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/carrinho" class="carrinho">
                <div class="img">
                    <img src="${pageContext.request.contextPath}/../assets/img/carrinho.png" alt="carrinho">
                </div>
            </a>
        </header>
        <script src="${pageContext.request.contextPath}/assets/js/logins.js"></script>
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
    <%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/carrinho.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <title>Carrinho</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty sessionScope.id_cliente}">
            <%@ include file="/assets/components/header-cliente.jsp" %>
        </c:when>
        <c:otherwise>
            <%@ include file="/assets/components/header.jsp" %>
        </c:otherwise>
    </c:choose>
    <section>
        <div class="card-content">
            <c:choose>
                <c:when test="${empty carrinho}">
                    <div class="carrinho-vazio">
                        <h2>Carrinho vazio</h2>
                        <p>Adicione produtos ao seu carrinho para continuar.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${carrinho}">
                        <div class="card-produto">
                            <div class="img">
                                <c:forEach var="produto" items="${produtos}">
                                    <c:if test="${produto.id == item.produto.id}">
                                        <img src="${pageContext.request.contextPath}/${produto.imagens[0].caminho}" alt="${produto.nome}">
                                    </c:if>
                                </c:forEach>
                            </div>

                            <div class="titulo-produto">
                                <h1>${item.produto.nome}</h1>
                            </div>

                            <div class="contador">
                                <div class="quantidade-controls">
                                    <button onclick="alterarQuantidade(${item.produto.id}, 'diminuir')" class="btn-quantidade">-</button>
                                    <span class="quantidade">${item.quantidade}</span>
                                    <button onclick="alterarQuantidade(${item.produto.id}, 'aumentar')" class="btn-quantidade">+</button>
                                </div>
                                <button onclick="removerProduto(${item.produto.id})" class="btn-remover">Remover</button>
                            </div>

                            <div class="preco">
                                <p>Preço unitário:</p>
                                <h2>R$ <fmt:formatNumber value="${item.produto.preco}" pattern="#,##0.00" /></h2>
                                <p>Subtotal:</p>
                                <h3>R$ <fmt:formatNumber value="${item.subtotal}" pattern="#,##0.00" /></h3>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="resumo">
            <div class="card1">
                <div class="valor-produto">
                    <p class="description">Subtotal:</p>
                    <p class="valor">R$ <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" /></p>
                </div>

                <div class="frete-section">
                    <h3>Calcular Frete</h3>
                    <form method="post" action="carrinho" onsubmit="calcularFrete(event)">
                        <input type="hidden" name="action" value="calcularFrete">
                        <div class="frete-options">
                            <label>
                                <input type="radio" name="frete" value="economico" onchange="atualizarFrete()" ${frete == 15.00 ? 'checked' : ''}>
                                Econômico - R$ 15,00 (5-7 dias)
                            </label>
                            <label>
                                <input type="radio" name="frete" value="normal" onchange="atualizarFrete()" ${frete == 25.00 ? 'checked' : ''}>
                                Normal - R$ 25,00 (3-5 dias)
                            </label>
                            <label>
                                <input type="radio" name="frete" value="expresso" onchange="atualizarFrete()" ${frete == 35.00 ? 'checked' : ''}>
                                Expresso - R$ 35,00 (1-2 dias)
                            </label>
                        </div>
                    </form>
                </div>

                <div class="frete-valor">
                    <p class="description">Frete:</p>
                    <p class="valor">R$ <fmt:formatNumber value="${frete}" pattern="#,##0.00" /></p>
                </div>

                <hr>

                <div class="total-produto">
                    <p class="description">Total a pagar:</p>
                    <p class="total">R$ <fmt:formatNumber value="${total}" pattern="#,##0.00" /></p>
                </div>
            </div>

            <div class="cardbutton">
                <div>
                    <div>
                    <a href="">Finalizar Compra</a>
                    </div>
                    <div>
                    <a href="index.jsp">Continuar Comprando</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="/assets/components/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/js/carrinho.js"></script>
</body>
</html>

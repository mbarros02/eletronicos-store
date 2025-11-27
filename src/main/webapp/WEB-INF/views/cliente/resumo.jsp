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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/carrinho.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <title>Resumo do Pedido</title>
</head>
<body>
    <%@ include file="/assets/components/header-cliente.jsp" %>
    <section>
        <div class="card-content">
            <c:choose>
                <c:when test="${empty carrinho}">
                    <div class="carrinho-vazio">
                        <h2>Carrinho vazio</h2>
                        <p>Adicione produtos ao seu carrinho para continuar</p>
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
                                    <span class="quantidade">Qtd: ${item.quantidade}</span>
                                </div>
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
                   <h3>Endereço de Entrega</h3><br>
                   <p>${endereco.logradouro}, ${endereco.bairro}</p>
                   <br>
                   <h3>Forma de Pagamento</h3><br>
                   <p>${formaPagamento}</p>

                   <c:if test="${formaPagamento == 'cartao'}">
                       <p>Nome no Cartão: ${nomeCartao}</p><br>
                       <p>Parcelas: ${parcelas}</p>
                   </c:if>

                   <button type="button" onclick="window.history.back()">Voltar</button>

                   <form method="post" action="pedido" style="display:inline;">
                       <input type="hidden" name="endereco" value="${endereco.logradouro}, ${endereco.bairro}">
                       <input type="hidden" name="formaPagamento" value="${formaPagamento}">
                       <input type="hidden" name="nomeCartao" value="${nomeCartao}">
                       <input type="hidden" name="parcelas" value="${parcelas}">
                       <input type="hidden" name="subtotal" value="${subtotal}">
                       <input type="hidden" name="frete" value="${frete}">
                       <input type="hidden" name="total" value="${total}">
                       <button type="submit">Finalizar</button>
                   </form>
                </div>
            </div>
        </div>
    </section>

    <%@ include file="/assets/components/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/js/carrinho.js"></script>
</body>
</html>

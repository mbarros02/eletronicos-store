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
    <title>Checkout</title>
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
                    <form method="post" action="checkout">
                        <div class="frete-options">
                            <h3>Endereços</h3>
                            <c:forEach var="endereco" items="${enderecos}">
                                <label>
                                    <input type="radio" name="enderecoSelecionado" value="${endereco.id}" required>
                                    ${endereco.logradouro}, ${endereco.bairro}
                                </label><br>
                            </c:forEach>
                        </div>

                        <div class="frete-options">
                            <h3>Forma de Pagamento</h3>
                            <label>
                                <input type="radio" name="formaPagamento" value="Boleto" required> Boleto Bancário
                            </label><br>
                            <label>
                                <input type="radio" name="formaPagamento" value="Cartão" required> Cartão de Crédito
                            </label>
                        </div>

                        <div id="cartao-info" style="display: none; margin-top: 10px;">
                            <p>Dados do Cartão</p>
                            <label>Número do Cartão:
                                <input type="text" name="numeroCartao" maxlength="16" oninput="this.value=this.value.replace(/[^0-9]/g,'')">
                            </label><br>
                            <label>Nome Completo:
                                <input type="text" name="nomeCartao">
                            </label><br>
                            <label>Código Verificador (CVV):
                                <input type="text" name="codigoVerificador" maxlength="3" oninput="this.value=this.value.replace(/[^0-9]/g,'')">
                            </label><br>
                            <label>Data de Vencimento:
                                <input type="date" name="dataVencimento">
                            </label><br>
                            <label>Quantidade de Parcelas:
                                <select name="parcelas">
                                    <option value=""></option>
                                    <option value="1x">1x</option>
                                    <option value="2x">2x</option>
                                    <option value="3x">3x</option>
                                    <option value="4x">4x</option>
                                    <option value="5x">5x</option>
                                    <option value="6x">6x</option>
                                    <option value="7x">7x</option>
                                    <option value="8x">8x</option>
                                    <option value="9x">9x</option>
                                    <option value="10x">10x</option>
                                </select>
                            </label>
                        </div>
                        <button type="button" onclick="window.history.back()">Voltar</button>
                        <button type="submit">Próximo</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="/assets/components/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/js/carrinho.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/pagamento.js"></script>
</body>
</html>

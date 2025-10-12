<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/home.css?99999999">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <title>Produtos - Eletronic Store</title>
</head>

<body>
    <%@ include file="Components/Header.jsp" %>

    <section id="Produtos">
        <div class="col">
            <div class="title">
                <h1>Produtos Disponíveis</h1>
            </div>
        </div>
        <div class="content">
            <c:forEach var="produto" items="${produtos}">
                <div class="card">
                    <div class="img">
                        <img src="${pageContext.request.contextPath}/${produto.imagemPrincipal.caminho}" alt="${produto.nome}">
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

    <%@ include file="Components/footer.jsp" %>
</body>
</html>

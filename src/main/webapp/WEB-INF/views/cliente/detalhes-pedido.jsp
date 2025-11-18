<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/list-usuario.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <style>
            body {
                font-family: 'Inter', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            h1 {
                color: #3B82F6;
                text-align: center;
                margin-bottom: 25px;
            }

            .pedido-detalhes {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 6px;
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
            }

            .pedido-detalhes p {
                margin: 10px 0;
            }

            .pedido-detalhes strong {
                color: #000;
            }

            a {
                display: inline-block;
                margin-top: 20px;
                text-decoration: none;
                background-color: #3B82F6;
                color: #fff;
                padding: 8px 15px;
                border-radius: 4px;
            }

            a:hover {
                background-color: #2563eb;
            }
        </style>
    <title>Detalhes</title>
</head>
<body>
    <h1>Detalhes do Pedido #${pedido.numeroPedido}</h1>
    <p>
        <strong>Data:</strong>
        <fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy HH:mm:ss"/>
    </p>
    <p><strong>Endereço de Entrega:</strong> ${pedido.enderecoEntrega}</p>
    <p><strong>Forma de Pagamento:</strong> ${pedido.formaPagamento}</p>
    <c:if test="${not empty pedido.nomeCartao}">
        <p><strong>Nome no Cartão:</strong> ${pedido.nomeCartao}</p>
    </c:if>

    <c:if test="${not empty pedido.parcelas}">
        <p><strong>Parcelas:</strong> ${pedido.parcelas}</p>
    </c:if>
    <p><strong>Subtotal:</strong> R$ ${pedido.subtotal}</p>
    <p><strong>Frete:</strong> R$ ${pedido.frete}</p>
    <p><strong>Total:</strong> R$ ${pedido.total}</p>
    <p><strong>Status:</strong> ${pedido.status}</p>

    <h1>Itens</h1>

    <c:forEach var="item" items="${itens}">
        <p><strong>Produto:</strong> ${item.nomeProduto} - <strong>Quantidade:</strong> ${item.quantidade} - <strong>Preço:</strong> ${item.precoUnitario}</p><br/>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/pedido?id_cliente=${sessionScope.cliente.id}">Meus Pedidos</a>
</body>
</html>


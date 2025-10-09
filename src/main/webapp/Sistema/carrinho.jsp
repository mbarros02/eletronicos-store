<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eletronicosstore.models.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");
    if (carrinho == null) carrinho = new java.util.ArrayList<>();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Seu Carrinho</title>
    <link rel="stylesheet" href="css/carrinho.css">
</head>
<body>
    <%@ include file="Components/Header.jsp" %>

    <h1>Produtos no Carrinho</h1>
    <c:forEach var="produto" items="${carrinho}">
        <div class="produto">
            <h3>${produto.nome}</h3>
            <p>${produto.descricao}</p>
            <p>R$ ${produto.preco}</p>
        </div>
    </c:forEach>

    <%@ include file="Components/footer.jsp" %>
</body>
</html>

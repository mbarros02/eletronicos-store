<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <title>TechStore</title>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/logout" class="Logo">
        <div class="img-logo">
            <h1 class="Logo">TechStore</h1>
        </div>
    </a>

    <c:if test="${empty sessionScope.usuarioAtual}">
        <div class="links">
            <div>
                <a href="${pageContext.request.contextPath}/" class="Produtos">Produtos</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/" class="Categoria">Categoria</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/" class="Sobre">Sobre Nós</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/login?action=login-cliente" class="Login">Login</a>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/carrinho" class="carrinho">
            <div class="img">
                <img src="${pageContext.request.contextPath}/../assets/img/carrinho.png" alt="">
            </div>
        </a>
    </c:if>
</header>
</body>
</html>

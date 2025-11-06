<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

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
            <a href="${pageContext.request.contextPath}/cliente?action=home" class="Logo">
                <div class="img-logo">
                    <h1 class="Logo">TechStore</h1>
                </div>
            </a>
            <div class="links">
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
            <div class="content">
                                <a id="btnCliente" class="name">Olá, ${sessionScope.cliente.nome}</a>
                            </div>

            <a href="${pageContext.request.contextPath}/carrinho" class="carrinho">
                <div class="img">
                    <img src="${pageContext.request.contextPath}/../assets/img/carrinho.png" alt="carrinho">
                </div>
            </a>
    </header>
    <div id="opcoesCliente" class="opcoes" style="display: none;">
        <a href="${pageContext.request.contextPath}/cliente?action=alterar" class="Login">Alterar Dados</a></br>
        <a href="${pageContext.request.contextPath}/endereco?action=listar&id_cliente=${sessionScope.cliente.id}" class="Login">Endereços</a></br>
        <a href="${pageContext.request.contextPath}/pedido?id_cliente=${sessionScope.cliente.id}" class="Login">Pedidos</a><br>
        <a href="${pageContext.request.contextPath}/logout" class="Login" style="color: red;">Sair</a>
    </div>
    <script src="${pageContext.request.contextPath}/../assets/js/opcoes-cliente.js"></script>
</body>
</html>
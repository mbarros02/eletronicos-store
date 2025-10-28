<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/list-usuario.css">

    <!-- Remover link stylesheet abaixo ⬇ (colocado de forma provisória) Ass: Nicolas -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <title>Endereços</title>
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
                <button id="btnLogin" class="Login">Olá, ${sessionScope.cliente.nome}</button>
            </div>
            <div id="opcoesLogin" class="opcoes">
                <a href="${pageContext.request.contextPath}/cliente?action=alterar" class="Login">Alterar Dados</a></br>
                <a href="${pageContext.request.contextPath}/endereco?action=listar&id_cliente=${sessionScope.cliente.id}" class="Login">Endereços</a></br>
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

<section>
    <div class="dashboard-container">
        <div class="content-area">
            <div class="content-header">
                <h1>Meus Endereços</h1>
                <a href="endereco?action=cadastro&id_cliente=${sessionScope.cliente.id}" class="btn btn-primary">Novo Endereço</a>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>CEP</th>
                            <th>Logradouro</th>
                            <th>Bairro</th>
                            <th>Cidade</th>
                            <th>UF</th>
                            <th>Tipo</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="endereco" items="${enderecos}">
                            <tr>
                                <td>${endereco.id}</td>
                                <td>${endereco.cep}</td>
                                <td>${endereco.logradouro}</td>
                                <td>${endereco.bairro}</td>
                                <td>${endereco.localidade}</td>
                                <td>${endereco.uf}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${endereco.tipoEndereco == 'F'}">Faturamento</c:when>
                                        <c:otherwise>Entrega</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${endereco.status == 1}">Ativo</c:when>
                                        <c:otherwise>Inativo</c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="actions">
                                    <div class="content-action">
                                        <c:choose>
                                            <c:when test="${endereco.status == 1}">
                                                <a class="inativar" href="endereco?action=inativar&id=${endereco.id}&id_cliente=${sessionScope.cliente.id}" title="Inativar">Inativar</a>
                                            </c:when>
                                            <c:otherwise>
                                               <a class="inativar" href="endereco?action=reativar&id=${endereco.id}&id_cliente=${sessionScope.cliente.id}" title="Ativar">Reativar</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

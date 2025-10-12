<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Usuários</title>
    <link rel="stylesheet" href="css/home.css" />
    <link rel="stylesheet" href="css/list-usuario.css" />
</head>
<body>
<header>
    <a href="/" class="Logo">
        <div class="img-logo">
            <h1 class="Logo">TechStore</h1>
        </div>
    </a>
    <div class="links">
        <div>
            <a href="\" class="Inicio">Inicio</a>
        </div>
        <div>
            <a href="\" class="Produtos">Produtos</a>
        </div>
        <div>
            <a href="\" class="Categoria">Categoria</a>
        </div>
        <div>
            <a href="\" class="Sobre">Sobre Nós</a>
        </div>
        <div>
            <a href="\" class="Login">Login</a>
        </div>
    </div>
    <a href="" class="carrinho">
        <div class="img">
            <img src="./Img/Shopping Cart.png" alt="">
        </div>
    </a>
</header>
<section>
    <div class="navbar">
        <div>
            <div>
                <a href="usuario?action=listar">Usuario</a>
            </div>
            <div>
                <a href="produto?action=listar">Produto</a>
            </div>
        </div>
    </div>
    <div class="barra"></div>
    <div class="dashboard-container">
        <div class="content-area">
            <div class="content-header">
                <h1>Usuários</h1>
                <a href="cad-usuario.jsp" class="btn btn-primary">Novo Usuário</a>
            </div>
            <!-- Debug info -->
            <div style="background: #f0f0f0; padding: 10px; margin: 10px 0;">
                <p>Debug: totalPaginas = ${totalPaginas}, paginaAtual = ${paginaAtual}, totalUsuarios = ${usuarios.size()}</p>
            </div>
            <form method="get" action="usuario" class="search-form">
                <input type="hidden" name="action" value="listar" />
                <input type="text" name="filtroNome" placeholder="Buscar usuário por nome" value="${filtroNome}" />
                <button type="submit">Buscar</button>
            </form>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Email</th>
                            <th>Grupo</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td>${usuario.id}</td>
                                <td>${usuario.nome}</td>
                                <td>${usuario.cpf}</td>
                                <td>${usuario.email}</td>
                                <td>${usuario.nomeGrupo}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${usuario.status}">
                                            Ativo
                                        </c:when>
                                        <c:otherwise>
                                            Inativo
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="usuario?action=alterarForm&id=${usuario.id}" class="button">Alterar</a>
                                    <a href="usuario?action=trocarStatus&id=${usuario.id}" class="button">${usuario.status ? 'Desativar' : 'Ativar'}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${paginaAtual > 1}">
                    <a href="usuario?action=listar&filtroNome=${filtroNome}&pagina=${paginaAtual - 1}">Anterior</a>
                </c:if>
                <c:if test="${totalPaginas != null && totalPaginas > 0}">
                    <c:forEach var="i" begin="1" end="${totalPaginas}" step="1">
                        <c:choose>
                            <c:when test="${i == paginaAtual}">
                                <span class="current">${i}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="usuario?action=listar&filtroNome=${filtroNome}&pagina=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                <c:if test="${paginaAtual < totalPaginas}">
                    <a href="usuario?action=listar&filtroNome=${filtroNome}&pagina=${paginaAtual + 1}">Próxima</a>
                </c:if>
            </div>
        </div>
    </div>
</section>
<footer>
    <div class="top">
        <div class="col1">
            <div class="title">
                <h1>TechStore</h1>
            </div>
            <div class="text">
                <p>Sua loja online de eletrônicos com a mais alta tecnologia e os melhores preços.</p>
            </div>
        </div>
        <div class="col2">
            <div class="title">
                <h1>Links Rápidos</h1>
            </div>
            <div class="link-footer">
                <a href="">Incio</a>
                <a href="">Produtos</a>
                <a href="">Categoria</a>
                <a href="">Sobre nós</a>
            </div>
        </div>
        <div class="col3">
            <div class="title">
                <h1>Contato</h1>
            </div>
            <div class="contatos">
                <div class="email">
                    <p>Email: contato@techstore.com</p>
                </div>
                <div class="telefone">
                    <p>Telefone: (XX) XXXX-XXXX</p>
                </div>
            </div>
        </div>
    </div>
    <div class="barra"></div>
    <div class="rotulo">
        <p>© 2025 TechStore. Todos os direitos reservados.</p>
    </div>
</footer>
</body>
</html>

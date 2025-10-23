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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">

    <!-- Remover link stylesheet abaixo ⬇ (colocado de forma provisória) Ass: Nicolas  -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <title>TechStore</title>
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
                    <button id="btnLogin" class="Login">Olá, ${cliente.nome}</button>
                </div>
                <div id="opcoesLogin" class="opcoes">
                    <a href="${pageContext.request.contextPath}/cliente?action=alterar" class="Login">Alterar Dados</a>
                    <a href="/index.jsp" class="Login">Sair</a>
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
            <div class="navbar">
                <div>
                    <c:if test="${sessionScope.usuarioAtual != null && sessionScope.usuarioAtual.idGrupo == 1}">
                        <div>
                            <img class="img" src="${pageContext.request.contextPath}/../assets/img/usuario.png" alt="Usuário"/>
                            <a href="usuario?action=listar">Usuario</a>
                        </div>
                    </c:if>
                    <div>
                        <img class="img" src="${pageContext.request.contextPath}/../assets/img/produto.png" alt="Produto"/>
                        <a href="produto?action=listar">Produto</a>
                    </div>
                </div>
            </div>

            <div class="barra"></div>

            <div class="dashboard-container">
                <div class="content-area">
                    <div class="content-header">
                        <h1>Produtos</h1>
                        <c:if test="${sessionScope.usuarioAtual == null || sessionScope.usuarioAtual.idGrupo != 2}">
                            <a href="produto?action=incluir" class="btn btn-primary" title="Cadastrar Produto">Novo Produto</a>
                        </c:if>
                    </div>

                    <form method="get" action="produto" onsubmit="return unificarFiltro()">
                        <input type="hidden" name="action" value="listar" />
                        <input type="text" name="filtroNome" placeholder="Nome" value="${filtroNome}" />
                        <input type="text" name="filtroId" placeholder="ID" value="${filtroId}" />
                        <button type="submit">Filtrar</button>
                    </form>

                    <script>
                        function unificarFiltro() {
                            var nome = document.querySelector('input[name="filtroNome"]');
                            var id = document.querySelector('input[name="filtroId"]');
                            if (id && id.value && id.value.trim().length > 0) {
                                nome.value = '';
                            } else if (nome && nome.value && nome.value.trim().length > 0) {
                                id.value = '';
                            }
                            return true;
                        }
                    </script>

                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nome</th>
                                    <th>Estoque</th>
                                    <th>Valor</th>
                                    <th>Status</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="produto" items="${produtos}">
                                    <tr>
                                        <td>${produto.id}</td>
                                        <td>${produto.nome}</td>
                                        <td>${produto.qtdEstoque}</td>
                                        <td>R$ <fmt:formatNumber value="${produto.preco}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${produto.status == 1}">Ativo</c:when>
                                                <c:otherwise>Inativo</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="actions">
                                            <div class="content-action">
                                                <a href="produto?action=alterarForm&id=${produto.id}" title="Alterar">
                                                    <img src="${pageContext.request.contextPath}/../assets/img/alterar.png" alt="Alterar"/>
                                                </a>
                                                <c:if test="${sessionScope.usuarioAtual != null && sessionScope.usuarioAtual.idGrupo != 2}">
                                                    <c:choose>
                                                        <c:when test="${produto.status == 1}">
                                                            <a class="inativar" href="produto?action=inativar&id=${produto.id}" title="Inativar">Inativar</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="inativar" href="produto?action=reativar&id=${produto.id}" title="Reativar">Reativar</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <a class="inativar" href="produto?action=visualizar&id=${produto.id}" title="Visualizar">Visualizar</a>
                                                </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <c:if test="${totalPaginas > 1}">
                        <div class="pagination">
                            <c:if test="${pagina > 1}">
                                <a href="produto?action=listar&pagina=${pagina - 1}&filtroNome=${filtroNome}&filtroId=${filtroId}" class="btn btn-primary">Anterior</a>
                            </c:if>
                            <c:if test="${pagina < totalPaginas}">
                                <a href="produto?action=listar&pagina=${pagina + 1}&filtroNome=${filtroNome}&filtroId=${filtroId}" class="btn btn-primary">Próxima</a>
                            </c:if>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
    <%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

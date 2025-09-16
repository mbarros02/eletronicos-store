<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Listagem de Produtos</title>
        <link rel="stylesheet" href="css/list-usuario.css">
        <link rel="stylesheet" href="../front/cadastro.css">
        <style>
            .btn-add { font-size: 1.5em; padding: 0.2em 0.6em; margin-left: 1em; }
            .pagination { margin-top: 1em; }
            .pagination a { margin: 0 0.2em; text-decoration: none; }
            .actions a { margin: 0 0.2em; }
        </style>
    </head>
<body>
    <h1>Produtos</h1>
    <div>Total de resultados: <strong>${totalProdutos}</strong></div>
    <form method="get" action="produto">
        <input type="hidden" name="action" value="listar" />
        <table>
            <tr>
                <td>Buscar por nome:</td>
                <td><input type="text" name="filtroNome" placeholder="Nome" value="${filtroNome}" /></td>
                <td style="width:16px"></td>
                <td>Buscar por ID:</td>
                <td><input type="text" name="filtroId" placeholder="ID" value="${filtroId}" /></td>
                <td style="width:16px"></td>
                <td><button type="submit">Buscar</button></td>
                <td style="width:16px"></td>
                <c:if test="${sessionScope.usuarioAtual == null || sessionScope.usuarioAtual.idGrupo != 2}">
                    <td><a href="produto?action=incluir" class="btn-add" title="Cadastrar Produto">+</a></td>
                </c:if>
            </tr>
        </table>
    </form>
    <table border="1" width="100%" style="margin-top:1em;">
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
                        <a href="produto?action=alterarForm&id=${produto.id}" title="Alterar">Alterar</a>
                        <c:if test="${sessionScope.usuarioAtual != null && sessionScope.usuarioAtual.idGrupo != 2}">
                            <c:choose>
                                <c:when test="${produto.status == 1}">
                                    <a href="produto?action=inativar&id=${produto.id}" title="Inativar">Inativar</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="produto?action=reativar&id=${produto.id}" title="Reativar">Reativar</a>
                                </c:otherwise>
                            </c:choose>
                            <a href="produto?action=visualizar&id=${produto.id}" title="Visualizar">Visualizar</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <c:if test="${pagina > 1}">
            <a href="produto?action=listar&pagina=${pagina - 1}&filtroNome=${filtroNome}&filtroId=${filtroId}">Anterior</a>
        </c:if>
        <c:forEach begin="1" end="${totalPaginas}" var="i">
            <c:choose>
                <c:when test="${i == pagina}"><strong>${i}</strong></c:when>
                <c:otherwise>
                    <a href="produto?action=listar&pagina=${i}&filtroNome=${filtroNome}&filtroId=${filtroId}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${pagina < totalPaginas}">
            <a href="produto?action=listar&pagina=${pagina + 1}&filtroNome=${filtroNome}&filtroId=${filtroId}">Próxima</a>
        </c:if>
    </div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/list-usuario.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <title>Pedidos</title>
</head>
<body>
    <%@ include file="/assets/components/header.jsp" %>
    <section>
        <div class="navbar">
            <div>
                <div>
                    <img class="img" src="${pageContext.request.contextPath}/../assets/img/produto.png" alt="Produto"/>
                    <a href="produto?action=listar">Produto</a>
                </div>
                <div>
                    <img class="img" src="${pageContext.request.contextPath}/../assets/img/pedido.png" alt="Pedidos"/>
                    <a href="${pageContext.request.contextPath}/pedido?action=listar-pedido">Pedidos</a>
                </div>
            </div>
        </div>

        <div class="barra"></div>

        <div class="dashboard-container">
            <div class="content-area">
                <div class="content-header">
                    <h1>Pedidos</h1>
                </div>

                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                 <th>Data</th>
                                 <th>Nº Pedido</th>
                                 <th>Valor Total</th>
                                 <th>Status</th>
                                 <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${pedidos}">
                                <tr>
                                    <td>
                                        <fmt:formatDate value="${p.data}" pattern="dd/MM/yyyy HH:mm"/>
                                    </td>

                                    <td>${p.numeroPedido}</td>

                                    <td>R$ <fmt:formatNumber value="${p.total}" type="number" minFractionDigits="2"/></td>

                                    <td>
                                        <form action="${pageContext.request.contextPath}/pedido" method="post" style="display:inline;">
                                                <input type="hidden" name="action" value="alterar-status"/>
                                                <input type="hidden" name="idPedido" value="${p.idPedido}"/>

                                                <select name="novoStatus">
                                                       <option ${p.status == 'Aguardando pagamento' ? 'selected' : ''}>Aguardando pagamento</option>
                                                       <option ${p.status == 'Pagamento rejeitado' ? 'selected' : ''}>Pagamento rejeitado</option>
                                                       <option ${p.status == 'Pagamento com sucesso' ? 'selected' : ''}>Pagamento com sucesso</option>
                                                       <option ${p.status == 'Aguardando retirada' ? 'selected' : ''}>Aguardando retirada</option>
                                                       <option ${p.status == 'Em transito' ? 'selected' : ''}>Em transito</option>
                                                       <option ${p.status == 'Entregue' ? 'selected' : ''}>Entregue</option>
                                                </select>

                                    </td>

                                    <td>
                                            <button type="submit" class="btn btn-primary">Editar Pedido</button>
                                        </form>
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

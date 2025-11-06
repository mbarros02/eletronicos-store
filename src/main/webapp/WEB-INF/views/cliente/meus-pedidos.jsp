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
    <title>Meus Pedidos</title>
</head>
<body>
<%@ include file="/../../../assets/components/header-cliente.jsp" %>

<section>
    <div class="dashboard-container">
        <div class="content-area">
            <div class="content-header">
                <h1>Meus Pedidos</h1>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Número</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Data</th>
                            <th>Detalhes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pedido" items="${pedidos}">
                            <tr>
                                <td>${pedido.numeroPedido}</td>
                                <td>R$ <fmt:formatNumber value="${pedido.total}" type="currency" currencySymbol="R$"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${pedido.status == 'Aguardando pagamento'}">Aguardando Pagamento</c:when>
                                        <c:when test="${pedido.status == 'Pago'}">Pago</c:when>
                                        <c:when test="${pedido.status == 'Enviado'}">Enviado</c:when>
                                        <c:otherwise>${pedido.status}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                <td>
                                    <a class="inativar" href="${pageContext.request.contextPath}/pedido?id=${pedido.idPedido}">Ver detalhes</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty pedidos}">
                            <tr>
                                <td colspan="5">Nenhum pedido encontrado.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

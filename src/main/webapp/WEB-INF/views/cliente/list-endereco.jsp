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
    <title>Endereços</title>
</head>
<body>
<%@ include file="/../../../assets/components/header-cliente.jsp" %>
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
                            <th>Principal</th>
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
                                        <c:if test="${endereco.tipoEndereco != 'F'}">
                                            <c:choose>
                                                <c:when test="${endereco.status == 1}">
                                                    <a class="inativar" href="endereco?action=inativar&id=${endereco.id}&id_cliente=${sessionScope.cliente.id}" title="Inativar">Inativar</a>
                                                </c:when>
                                                <c:otherwise>
                                                   <a class="inativar" href="endereco?action=reativar&id=${endereco.id}&id_cliente=${sessionScope.cliente.id}" title="Ativar">Reativar</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>
                                </td>
                                <td>
                                    <c:if test="${endereco.tipoEndereco != 'F' && endereco.status == 1}">
                                        <c:if test="${endereco.tipoEndereco != 'F' && endereco.status == 1}">
                                                <input type="radio" name="enderecoPrincipal" value="${endereco.id}"
                                                    onclick="definirPrincipal(${endereco.id})"
                                                    <c:if test="${endereco.principal != null && endereco.principal == 1}">checked</c:if> />
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<script>
function definirPrincipal(idEndereco) {
    fetch('endereco?action=definirPrincipal&id=' + idEndereco, {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            alert('Endereço principal atualizado com sucesso!');
        } else {
            alert('Erro ao atualizar endereço principal');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao atualizar endereço principal');
    });
}
</script>

<%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

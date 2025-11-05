<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meus Endereços - TechStore</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/cadastro.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/enderecos.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/assets/components/header.jsp" %>

<section id="enderecos">
    <div class="container">
        <h1>Meus Endereços</h1>

        <c:if test="${empty enderecos}">
            <p>Você ainda não possui endereços cadastrados.</p>
        </c:if>

        <div class="cards-container">
            <c:forEach var="endereco" items="${enderecos}">
                <div class="card-endereco">
                    <div class="card-content">
                        <p><strong>CEP:</strong> ${endereco.cep}</p>
                        <p><strong>Logradouro:</strong> ${endereco.logradouro}</p>
                        <p><strong>Bairro:</strong> ${endereco.bairro}</p>
                        <p><strong>Cidade:</strong> ${endereco.localidade} - ${endereco.uf}</p>
                        <p><strong>Complemento:</strong> ${endereco.complemento}</p>
                        <p><strong>Tipo:</strong>
                            <c:choose>
                                <c:when test="${endereco.tipoEndereco eq 'F'}">Faturamento</c:when>
                                <c:when test="${endereco.tipoEndereco eq 'E'}">Entrega</c:when>
                                <c:otherwise>Entrega + Faturamento</c:otherwise>
                            </c:choose>
                        </p>
                    </div>

                    <form action="alterar" method="post" class="form-alterar">
                        <input type="hidden" name="action" value="alterar">
                        <input type="hidden" name="id_endereco" value="${endereco.idEndereco}">
                        <input type="hidden" name="id_cliente" value="${endereco.idCliente}">

                        <label>Alterar Tipo:</label>
                        <select name="tipo_endereco" required>
                            <option value="E" ${endereco.tipoEndereco == 'E' ? 'selected' : ''}>Entrega</option>
                            <option value="F" ${endereco.tipoEndereco == 'F' ? 'selected' : ''}>Faturamento</option>
                            <option value="FE" ${endereco.tipoEndereco == 'FE' ? 'selected' : ''}>Entrega + Faturamento</option>
                        </select>

                        <button type="submit">Salvar</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<%@ include file="/assets/components/footer.jsp" %>
</body>
</html>
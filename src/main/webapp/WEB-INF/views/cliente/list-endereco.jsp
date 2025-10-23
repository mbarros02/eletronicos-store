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
    <title>Enderecos</title>
</head>
<body>
    <%@ include file="/assets/components/header.jsp" %>
                 <section id="login">
                        <div class="card">
                            <div class="title">
                                <h1>Meus Endereços</h1>
                            </div>

                            <c:choose>
                                <c:when test="${not empty enderecos}">
                                    <table border="1" class="tabela-enderecos" style="width:100%; border-collapse:collapse; text-align:left;">
                                        <thead>
                                            <tr>
                                                <th>CEP</th>
                                                <th>Logradouro</th>
                                                <th>Bairro</th>
                                                <th>Cidade</th>
                                                <th>UF</th>
                                                <th>Complemento</th>
                                                <th>Tipo</th>
                                                <th>Ações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="endereco" items="${enderecos}">
                                                <tr>
                                                    <td>${endereco.cep}</td>
                                                    <td>${endereco.logradouro}</td>
                                                    <td>${endereco.bairro}</td>
                                                    <td>${endereco.localidade}</td>
                                                    <td>${endereco.uf}</td>
                                                    <td>${endereco.complemento}</td>
                                                    <td>${endereco.tipoEndereco}</td>
                                                    <td>
                                                        <a href="${pageContext.request.contextPath}/endereco?action=alterar&id_endereco=${endereco.idEndereco}" class="Login">Alterar</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <p>Você ainda não possui endereços cadastrados.</p>
                                </c:otherwise>
                            </c:choose>

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

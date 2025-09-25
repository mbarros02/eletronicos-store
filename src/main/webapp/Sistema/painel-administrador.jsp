<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/list-usuario.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
        rel="stylesheet">
    <title>Document</title>
</head>

<body>
    <%@ include file="/Components/Header.jsp" %>
 <section>
        <div class="navbar">
            <div>
                <div>
                <img class="img" src=".././assets/Img/icons8-user-48.png" alt="Alterar"/>
                    <a href="usuario?action=listar">Usuario</a>
                </div>
                <div>
                <img class="img" src=".././assets/Img/icons8-product-30.png" alt="Alterar"/>
                    <a href="produto?action=listar">Produto</a>
                </div>
            </div>
        </div>
        <div class="barra"></div>
        <div class="dashboard-container">
            <div class="content-area">
                <div class="content-header">
                    <h1>Usuarios</h1>
                    <a href="usuario?action=incluir" class="btn btn-primary">Novo Usuário
                    </a>
                </div>
                <form method="get" action="usuario">
                    <input type="hidden" name="action" value="listar" />
                    <input type="text" name="filtroNome" placeholder="Filtrar por nome" value="${filtroNome}" />
                    <button type="submit">Filtrar</button>
                </form>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Status</th>
                                <th>Grupo</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td>${usuario.nome}</td>
                                    <td>${usuario.email}</td>
                                    <td>${usuario.status ? 'Ativo' : 'Inativo'}</td>
                                    <td>${usuario.nomeGrupo}</td>
                                    <td>
                                        <div class="content-action">
                                        <a href="usuario?action=alterarForm&id=${usuario.id}" class="button"><img src=".././assets/Img/icons8-pencil-30.png" alt="Alterar"/></a>
                                                                                <a class="inativar" href="usuario?action=trocarStatus&id=${usuario.id}"
                                                                                    class="button ${usuario.status ? 'danger' : ''}">
                                                                                    ${usuario.status ? 'Inativar' : 'Ativar'}
                                                                                </a>
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
    <%@ include file="/Components/footer.jsp" %>
</body>

</html>
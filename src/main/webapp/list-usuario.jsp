<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Usuários</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #000; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a.button { padding: 5px 10px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 3px; }
        a.button:hover { background-color: #45a049; }
        a.danger { background-color: #f44336; }
        a.danger:hover { background-color: #d32f2f; }
        form { margin-bottom: 10px; }
    </style>
</head>
<body>
    <form method="get" action="usuario">
        <input type="hidden" name="action" value="listar"/>
        <input type="text" name="filtroNome" placeholder="Filtrar por nome" value="${filtroNome}"/>
        <button type="submit">Filtrar</button>
    </form>
    <a href="usuario?action=incluir" class="button">+ Novo Usuário</a>
    <table>
        <tr>
            <th>Nome</th>
            <th>E-mail</th>
            <th>Status</th>
            <th>Grupo</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.nome}</td>
                <td>${usuario.email}</td>
                <td>${usuario.status ? 'Ativo' : 'Inativo'}</td>
                <td>${usuario.nomeGrupo}</td>
                <td>
                    <a href="usuario?action=alterarForm&id=${usuario.id}" class="button">Alterar</a>
                    <a href="usuario?action=trocarStatus&id=${usuario.id}"
                       class="button ${usuario.status ? 'danger' : ''}">
                       ${usuario.status ? 'Inativar' : 'Ativar'}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

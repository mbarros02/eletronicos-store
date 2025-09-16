<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Visualizar Produto</title>
    <style>
        .detail { max-width: 640px; margin: 24px auto; font-family: Arial, Helvetica, sans-serif; }
        .detail h1 { margin-bottom: 16px; }
        .detail .row { display: flex; margin: 8px 0; }
        .detail .label { width: 140px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="detail">
        <h1>Detalhes do Produto</h1>
        <div class="row"><div class="label">Código:</div><div>${produto.id}</div></div>
        <div class="row"><div class="label">Nome:</div><div>${produto.nome}</div></div>
        <div class="row"><div class="label">Avaliação:</div><div>${produto.avaliacao}</div></div>
        <div class="row"><div class="label">Descrição:</div><div>${produto.descricao}</div></div>
        <div class="row"><div class="label">Preço:</div><div>R$ ${produto.preco}</div></div>
        <div class="row"><div class="label">Estoque:</div><div>${produto.qtdEstoque}</div></div>
        <div class="row"><div class="label">Status:</div>
            <div>
                <c:choose>
                    <c:when test="${produto.status == 1}">Ativo</c:when>
                    <c:otherwise>Inativo</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div style="margin-top:16px;">
            <button onclick="window.location.href='produto?action=listar'">Voltar</button>
            <button onclick="window.location.href='produto?action=alterarForm&id=${produto.id}'">Alterar</button>
        </div>
    </div>
</body>
</html>

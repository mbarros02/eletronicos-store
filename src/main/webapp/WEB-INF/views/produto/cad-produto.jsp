<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/../assets/css/cadastro.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <title>Cadastro Produto</title>
</head>

<body>
    <%@ include file="/assets/components/header.jsp" %>
    <section id="login">
        <div class="card">
            <div class="title">
                <h1>TechStore</h1>
            </div>
            <form action="produto" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="cadastrar">

                <div class="login">
                    <label for="">Nome</label>
                    <input type="text" name="nome" maxlength="200" required>
                </div>

                <div class="login">
                    <label for="">Estoque</label>
                    <input type="number" name="estoque" required>
                </div>

                <div class="login">
                    <label for="">Preço</label>
                    <input type="number" name="preco" step="0.01" required>
                </div>

                <div>
                    <label for="">Avaliação</label>
                    <select name="avaliacao" required>
                        <option value=""></option>
                        <option value="0.5">0.5</option>
                        <option value="1">1.0</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2.0</option>
                        <option value="2.5">2.5</option>
                        <option value="3">3.0</option>
                        <option value="3.5">3.5</option>
                        <option value="4">4.0</option>
                        <option value="4.5">4.5</option>
                        <option value="5">5.0</option>
                    </select>
                </div>

                <div class="login">
                    <label>Descrição</label>
                    <textarea name="descricao" maxlength="2000" required></textarea>
                </div>

                <div class="login">
                    <label>Imagens:</label>
                    <input type="file" name="imagens" multiple required>
                </div>

                <div class="login">
                    <label>Imagem Principal (nome do arquivo):</label>
                    <input type="text" name="imagemPrincipal" placeholder="ex: foto1.jpg">
                </div>

                <div class="margem"></div>

                <div class="footer-card">
                    <div class="buton">
                        <button type="submit">Salvar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

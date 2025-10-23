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
    <title>Cadastro de Cliente</title>
</head>
<body>
    <%@ include file="/assets/components/header.jsp"%>
    <section id="login">
        <div class="card">
            <div class="title">
                <h1>Dados Pessoais</h1>
            </div>
            <form action="cliente" method="post">
                <input type="hidden" name="action" value="cadastro">
                <div class="login">
                    <label for="">Nome</label>
                    <input type="text" name="nome" required>
                </div>
                <div class="login">
                    <label for="">CPF</label>
                    <input type="text" name="cpf" required>
                </div>
                <div class="sexo">
                    <label for="">Sexo</label>
                    <select name="sexo">
                        <option value=""></option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
                <div class="dataNascimento">
                    <label>Data de Nascimento:</label>
                    <input type="date" name="dataNasc" required>
                </div>
                <div class="login">
                    <label for="">E-mail</label>
                    <input type="email" name="email" required>
                </div>
                <div class="login">
                    <label for="">Senha</label>
                    <input type="password" name="senha1" required>
                </div>
                <div class="login">
                    <label for="">Confimar Senha</label>
                    <input type="password" name="senha2" required>
                </div>
                <div class="margem"></div>
                <div class="footer-card">
                    <div class="buton">
                        <button type="submit" value="Cadastrar">Cadastrar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <%@ include file="/assets/components/footer.jsp" %>
</body>
</html>
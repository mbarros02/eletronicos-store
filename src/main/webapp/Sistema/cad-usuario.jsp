<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".././css/cadastro.css?0000">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
        rel="stylesheet">
    <title>Document</title>
</head>

<body>
    <%@ include file=".././Components/Header.jsp" %>

    <section id="login">
        <div class="card">
            <div class="title">
                <h1>TechStore</h1>
            </div>
            <form action="usuario" method="post">
                <input type="hidden" name="action" value="cadastro">
                <div class="login">
                    <label for="">Nome</label>
                    <input type="text" name="nome" required>
                </div>
                <div class="login">
                    <label for="">CPF</label>
                    <input type="text" name="cpf" required>
                </div>
                <div class="login">
                    <label for="">E-mail</label>
                    <input type="email" name="email" required>
                </div>
                <div>
                    <label for="">Grupo</label>
                    <select  name="idGrupo" id="" required>
                        <option value=""></option>
                        <option value=1>Administrador</option>
                        <option value=2>Estoquista</option>
                    </select>
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

    <%@ include file=".././Components/footer.jsp" %>
</body>

</html>
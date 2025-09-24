<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css?0000">
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
            <form action="login" method="post">
            <input type="hidden" name="action" value="login">
                <div class="login">
                    <label for="">login</label>
                    <input type="email" name="email" required>
                </div>
                <div class="login">
                    <label for="">Senha</label>
                    <input type="password" name="senha"  required>
                </div>
                <div class="margem"></div>
                <div class="footer-card">
                    <div class="buton">
                        <button type="submit" value="Entrar">LOGIN</button>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <%@ include file=".././Components/footer.jsp" %>
</body>
</html>

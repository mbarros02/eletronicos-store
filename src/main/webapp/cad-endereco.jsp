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
                <h1>TechStore</h1>
            </div>
            <form action="endereco" method="post">
                <input type="hidden" name="action" value="cadastro">
                <div class="login">
                    <label for="">CEP</label>
                    <input type="text" name="cep" required>
                </div>
                <div class="login">
                    <label for="">Logradouro</label>
                    <input type="text" name="logradouro" required>
                </div>
                <div class="complemento">
                    <label>Complemento</label>
                    <input type="text" name="complemento" required>
                </div>
                <div class="login">
                    <label for="">Bairro</label>
                    <input type="text" name="bairro" required>
                </div>
                <div class="login">
                    <label for="">Localidade</label>
                    <input type="text" name="localidade" required>
                </div>
                <div class="login">
                    <label for="">Id Cliente</label>
                    <input type="number" name="id_cliente" required>
                </div>
                <div class="tipo">
                    <label for="">Tipo Endereço</label>
                    <select name="tipo_endereco">
                        <option value="F">Faturamento</option>
                        <option value="E">Entrega</option>
                    </select>
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
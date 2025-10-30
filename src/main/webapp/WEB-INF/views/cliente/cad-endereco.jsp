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
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/../assets/img/favicon.png">
    <title>Cadastro de Cliente</title>
</head>
<body>
    <%@ include file="/assets/components/header-cliente.jsp"%>
    <section id="login">
        <div class="card">
            <div class="title">
                <h1>Endereço</h1>
            </div>

            <form action="endereco" method="post">
                <input type="hidden" name="action" value="cadastro">

                <div class="login">
                    <label for="cep">CEP</label>
                    <input type="text" id="cep" name="cep" required>
                </div>

                <div class="login">
                    <label for="logradouro">Logradouro</label>
                    <input type="text" id="logradouro" name="logradouro">
                </div>

                <div class="complemento">
                    <label for="complemento">Complemento</label>
                    <input type="text" id="complemento" name="complemento">
                </div>

                <div class="login">
                    <label for="bairro">Bairro</label>
                    <input type="text" id="bairro" name="bairro">
                </div>

                <div class="login">
                    <label for="localidade">Localidade</label>
                    <input type="text" id="localidade" name="localidade">
                </div>

                <div class="login">
                    <label for="uf">UF</label>
                    <input type="text" id="uf" name="uf">
                </div>

               <div class="tipo">
                   <label for="tipo_endereco">Tipo Endereço</label>
                   <select id="tipo_endereco" name="tipo_endereco" style="pointer-events: none; appearance: none;">
                       <option value="F" <c:if test="${totalEnderecos == 0}">selected</c:if>>Faturamento</option>
                       <option value="E" <c:if test="${totalEnderecos > 0}">selected</c:if>>Entrega</option>
                   </select>
               </div>

                <div class="login">
                    <input type="hidden" id="id_cliente" name="id_cliente" value="${cliente.id}" readonly>
                </div>

                <div class="margem"></div>
                <div class="footer-card">
                    <div class="buton">
                        <button type="submit">Cadastrar</button>
                    </div>
                    <c:if test="${totalEnderecos > 0}">
                        <div class="buton">
                            <a href="${pageContext.request.contextPath}/cliente?action=home" class="link">Home</a>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </section>

    <%@ include file="/assets/components/footer.jsp"%>
</body>
</html>
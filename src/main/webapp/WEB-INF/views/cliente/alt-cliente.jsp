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
    <title>Alterar</title>
</head>
<body>
    <%@ include file="/../../../assets/components/header-cliente.jsp" %>
    <section id="login">
        <div class="card">
            <div class="title">
                <h1>TechStore</h1>
            </div>
            <form action="cliente" method="post">
                <input type="hidden" name="action" value="alterar">
                <input type="hidden" name="id" value="${cliente.id}" required readonly>

                <div class="login">
                    <label for="">Nome</label>
                    <input type="text" name="nome" value="${cliente.nome}" required>
                </div>
                <div class="dataNascimento">
                    <label>Data de Nascimento:</label>
                    <input type="date" name="dataNasc" value='${cliente.dataNascimento}' pattern='dd-MM-yyyy' required>
                </div>
                <div class="sexo">
                    <label for="">Sexo</label>
                    <select name="sexo">
                        <option value="Masculino" <c:if test="${cliente.sexo == 'Masculino'}">selected="selected"</c:if>>Masculino</option>
                        <option value="Feminino" <c:if test="${cliente.sexo == 'Feminino'}">selected="selected"</c:if>>Feminino</option>
                    </select>
                </div>
                <div class="login">
                    <label for="">Senha</label>
                    <input type="password" name="senha1" required>
                </div>

                <div class="login">
                    <label for="">Confirmar Senha</label>
                    <input type="password" name="senha2" required>
                </div>

                <div class="margem"></div>

                <div class="footer-card">
                    <div class="buton">
                        <button type="submit" value="Atualizar">Atualizar</button>
                    </div>
                    <div class="buton">
                        <a href="endereco?action=cadastro&id_cliente=${sessionScope.cliente.id}" class="link">Adicionar Endereço</a>
                    </div>
                </div>
            </form>
        </div>
    </section>
      <script>
        const nomeInput = document.querySelector('input[name="nome"]');

        nomeInput.addEventListener('blur', function() {
            const valor = nomeInput.value.trim();
            const partes = valor.split(' ').filter(p => p.length > 0);

            if (partes.length < 2 || partes[0].length < 3 || partes[partes.length - 1].length < 3) {
                alert('Digite o nome e o sobrenome, cada um com pelo menos 3 caracteres.');
            }
        });

        document.getElementById('formCadastro').addEventListener('submit', function(event) {
            const valor = nomeInput.value.trim();
            const partes = valor.split(' ').filter(p => p.length > 0);

            if (partes.length < 2 || partes[0].length < 3 || partes[partes.length - 1].length < 3) {
                alert('Digite o nome e o sobrenome, cada um com pelo menos 3 caracteres.');
                event.preventDefault(); // Impede envio do formulário
            }
        });
        </script>
    <%@ include file="/assets/components/footer.jsp" %>
</body>
</html>

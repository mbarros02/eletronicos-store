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
                    <input name="cep" type="text" id="cep" value="" size="10" maxlength="9"
                                   onblur="pesquisacep(this.value);" /></label><br />
                </div>

                <div class="login">
                    <label for="logradouro">Rua</label>
                    <input type="text" id="logradouro" name="logradouro">
                </div>

                <div class="numero">
                    <label for="numero">Número</label>
                    <input type="text" id="numero" name="numero">
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
                    <label for="localidade">Cidade</label>
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

    <script>

        function limpa_formulário_cep() {
                //Limpa valores do formulário de cep.
                document.getElementById('logradouro').value=("");
                document.getElementById('complemento').value=("");
                document.getElementById('bairro').value=("");
                document.getElementById('localidade').value=("");
                document.getElementById('Uf').value=("");
        }

        function meu_callback(conteudo) {
            if (!("erro" in conteudo)) {
                //Atualiza os campos com os valores.
                document.getElementById('logradouro').value=(conteudo.logradouro);
                document.getElementById('complemento').value=(conteudo.complemento);
                document.getElementById('bairro').value=(conteudo.bairro);
                document.getElementById('localidade').value=(conteudo.localidade);
                document.getElementById('uf').value=(conteudo.uf);
            } //end if.
            else {
                //CEP não Encontrado.
                limpa_formulário_cep();
                alert("CEP não encontrado.");
            }
        }

        function pesquisacep(valor) {

            //Nova variável "cep" somente com dígitos.
            var cep = valor.replace(/\D/g, '');

            //Verifica se campo cep possui valor informado.
            if (cep != "") {

                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if(validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    document.getElementById('logradouro').value="...";
                    document.getElementById('complemento').value="...";
                    document.getElementById('bairro').value="...";
                    document.getElementById('localidade').value="...";
                    document.getElementById('uf').value="...";

                    //Cria um elemento javascript.
                    var script = document.createElement('script');

                    //Sincroniza com o callback.
                    script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                    //Insere script no documento e carrega o conteúdo.
                    document.body.appendChild(script);

                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
            else {
                //cep sem valor, limpa formulário.
                limpa_formulário_cep();
            }
        };

        </script>
</body>
</html>
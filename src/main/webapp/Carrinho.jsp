<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page isELIgnored="false" %>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href=".././css/carrinho.css">
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
                    rel="stylesheet">
                <title>Document</title>
            </head>

            <body>
                <%@ include file=".././Components/Header.jsp" %>

<section>
        <div class="card-content">
            <div class="card-produto">
                <div class="img">
                    <img src="closeup-tiro-de-uma-linda-borboleta-com-texturas-interessantes-em-uma-flor-de-petalas-de-laranja_181624-7640.jpg" alt="">
                </div>
                <div class="titulo-produto">
                    <div>
                        <h1>Mouse Gamer Redragon Harry Potter Grifinória, RGB, 12400 DPI, Sensor Pixart PMW3327, USB, Preto e Vermelho - HP-711G</h1>
                    </div>
                </div>
                <div class="contador">

                </div>
                <div class="preco">
                    <div>
                        <div><p>Preço do produto:</p></div>
                        <div><h2>R$ 169,00</h2></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="resumo">
            <div class="card1">
                <div class="valor-produto">
                    <p class="description">Valor do produto:</p>
                    <p class="valor">R$ 169,00</p>
                </div>
                <hr>
                <div class="total-produto">
                    <p class="description">Total a pagar:</p>
                    <p class="total">R$ 169,00</p>
                </div>
            </div>
            <div class="cardbutton">
                <div>
                    <div>
                        <a href="">Continuar</a>
                    </div>
                    <div>
                        <a href="">Voltar</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

                <%@ include file=".././Components/footer.jsp" %>
            </body>

</html>
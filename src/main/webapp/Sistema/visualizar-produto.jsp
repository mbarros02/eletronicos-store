<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@12/swiper-bundle.min.css" />
    <link rel="stylesheet" href=".././css/visualizar.css">
    <title>Document</title>
</head>
<body>
    <%@ include file=".././Components/Header.jsp" %>
    <section>
        <div class="swiper mySwiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img
                        src="closeup-tiro-de-uma-linda-borboleta-com-texturas-interessantes-em-uma-flor-de-petalas-de-laranja_181624-7640.jpg"
                        alt=""></div>
                <div class="swiper-slide">
                <%--aqui é para fazer o forech--%>
                <img src="closeup-tiro-de-uma-linda-borboleta-com-texturas-interessantes-em-uma-flor-de-petalas-de-laranja_181624-7640.jpg" alt="">
                <%--aqui é para fazer o forech--%>
                </div>
            </div>
            <div class="swiper-pagination"></div>
        </div>
        <div class="content-2">
            <div class="title">
                <h1>${produto.nome}</h1>
            </div>
            <div class="description">
                <p>
                    ${produto.descricao}
                </p>
            </div>
            <div class="preco">
                <h1>R$:${produto.preco}</h1>
            </div>
            <div class="avaliacao">
                <p>Avaliação: ${produto.avaliacao}</p>
            </div>
            <div class="buton">
                <button>Comprar</button>
            </div>
        </div>
    </section>
   <%@ include file=".././Components/footer.jsp" %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/swiper@12/swiper-bundle.min.js"></script>
    <script src=".././assets/Js/script.js"></script>
</body>

</html>
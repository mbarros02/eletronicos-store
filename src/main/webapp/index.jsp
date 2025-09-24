<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/home.css?99999999">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
        rel="stylesheet">
    <title>Eletronic </title>
</head>

<body>
    <%@ include file="Components/Header.jsp" %>
    <section id="pincipal">
        <div>
            <h1>Eletronicos</h1>
        </div>
    </section>
    <section id="Categoria">
        <div class="col">
            <div class="title">
                <h1>Explore nossas categorias</h1>
            </div>
        </div>
        <div class="content">
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Os melhores dispositivos móveis</p>
                        </div>
                        <div class="link-categoria">
                            <a href="">Ver produtos</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Os melhores dispositivos móveis</p>
                        </div>
                        <div class="link-categoria">
                            <a href="">Ver produtos</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Os melhores dispositivos móveis</p>
                        </div>
                        <div class="link-categoria">
                            <a href="">Ver produtos</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="Produtos">
        <div class="col">
            <div class="title">
                <h1>Nossos Produtos Mais Populares</h1>
            </div>
        </div>
        <div class="content">
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Com camera de alta resolução e bateria de longa duração</p>
                            <div>R$ 2.999,99</div>
                        </div>
                        <div class="buton">
                            <button>Adicionar ao Carrinho</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Com camera de alta resolução e bateria de longa duração</p>
                            <div>R$ 2.999,99</div>
                        </div>
                        <div class="buton">
                            <button>Adicionar ao Carrinho</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="img">
                    <img src="./Img/samsung-transparante-smartphone.avif" alt="">
                </div>
                <div class="content-card">
                    <div class="title-card">
                        <h3>Smartphones</h3>
                    </div>
                    <div class="description">
                        <div class="text">
                            <p>Com camera de alta resolução e bateria de longa duração</p>
                            <div>R$ 2.999,99</div>
                        </div>
                        <div class="buton">
                            <button>Adicionar ao Carrinho</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="Noticias">
        <div class="title">
            <h1>Receba as Últimas Novidades e Ofertas!</h1>
        </div>
        <div class="description">
            <h2>Inscreva-se em nossa newsletter para descontos exclusivos e lançamentos.</h2>
        </div>
        <div class="enviar">

            <input type="text" placeholder="Seu e-mail">
            <button>Inscrever</button>

        </div>
    </section>
    <%@ include file="Components/footer.jsp" %>
</body>

</html>
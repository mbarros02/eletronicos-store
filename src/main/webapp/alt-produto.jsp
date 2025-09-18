<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page isELIgnored="false" %>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="css/cadastro.css">
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
                    rel="stylesheet">
                <title>Document</title>
            </head>

            <body>
                <header>
                    <a href="/" class="Logo">
                        <div class="img-logo">
                            <h1 class="Logo">TechStore</h1>
                        </div>
                    </a>
                    <div class="links">
                        <div>
                            <a href="\" class="Inicio">Inicio</a>
                        </div>
                        <div>
                            <a href="\" class="Produtos">Produtos</a>
                        </div>
                        <div>
                            <a href="\" class="Categoria">Categoria</a>
                        </div>
                        <div>
                            <a href="\" class="Sobre">Sobre Nós</a>
                        </div>
                        <div>
                            <a href="\" class="Login">Login</a>
                        </div>
                    </div>
                    <a href="" class="carrinho">
                        <div class="img">
                            <img src="./Img/Shopping Cart.png" alt="">
                        </div>
                    </a>
                </header>

                <section id="login">
                    <div class="card">
                        <div class="title">
                            <h1>TechStore</h1>
                        </div>
                        <c:set var="isEstoquista"
                            value="${sessionScope.usuarioAtual != null && sessionScope.usuarioAtual.idGrupo == 2}" />
                        <form action="produto" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="alterar">
                            <c:choose>
                                <c:when test="${isEstoquista}">
                                    <div class="login">
                                        <label for="">Estoque</label>
                                        <input type="number" name="estoque" required value="${produto.qtdEstoque}">
                                        <input type="hidden" name="idproduto" value="${produto.id}">
                                        <div class="buton">
                                            <button type="submit">Salvar</button>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="login">
                                        <label for="">Nome</label>
                                        <input type="text" name="nome" maxlength="200" required value="${produto.nome}">
                                    </div>
                                    <div>
                                        <label for="">Avaliação</label>
                                        <select name="avaliacao" required>
                                            <option value=""></option>
                                            <option value="0.5" ${produto.avaliacao==0.5 ? 'selected' : '' }>0.5
                                            </option>
                                            <option value="1" ${produto.avaliacao==1.0 ? 'selected' : '' }>1.0</option>
                                            <option value="1.5" ${produto.avaliacao==1.5 ? 'selected' : '' }>1.5
                                            </option>
                                            <option value="2" ${produto.avaliacao==2.0 ? 'selected' : '' }>2.0</option>
                                            <option value="2.5" ${produto.avaliacao==2.5 ? 'selected' : '' }>2.5
                                            </option>
                                            <option value="3" ${produto.avaliacao==3.0 ? 'selected' : '' }>3.0</option>
                                            <option value="3.5" ${produto.avaliacao==3.5 ? 'selected' : '' }>3.5
                                            </option>
                                            <option value="4" ${produto.avaliacao==4.0 ? 'selected' : '' }>4.0</option>
                                            <option value="4.5" ${produto.avaliacao==4.5 ? 'selected' : '' }>4.5
                                            </option>
                                            <option value="5" ${produto.avaliacao==5.0 ? 'selected' : '' }>5.0</option>

                                        </select>
                                    </div>
                                    <div class="login">
                                        <label for="">Descrição</label>
                                        <textarea name="descricao" maxlength="2000"
                                            required>${produto.descricao}></textarea>
                                    </div>
                                    <div class="login">
                                        <label for="">Preço</label>
                                        <input type="number" name="preco" step="0.01" required value="${produto.preco}">
                                    </div>
                                    <div class="login">
                                        <label for="">Estoque</label>
                                        <input type="number" name="estoque" required value="${produto.qtdEstoque}">
                                    </div>
                                    <div class="login">
                                        <label for="">Imagens</label>
                                        <input type="file" name="imagens" multiple>
                                    </div>
                                    <div class="login">
                                        <label for="">Imagem Principal (nome do arquivo)</label>
                                        <input type="text" name="imagemPrincipal" placeholder="ex: foto1.jpg">
                                    </div>
                                    <div class="login">
                                        <label for="">Id</label>
                                        <input type="number" name="idproduto" required value="${produto.id}" readonly>
                                    </div>
                                    <div class="margem"></div>
                                    <div class="footer-card">
                                        <div class="buton">
                                            <button type="submit">Salvar</button>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </section>

                <footer>
                    <div class="top">
                        <div class="col1">
                            <div class="title">
                                <h1>TechStore</h1>
                            </div>
                            <div class="text">
                                <p>Sua loja online de eletrônicos com a mais alta tecnologia e os melhores preços.</p>
                            </div>
                        </div>
                        <div class="col2">
                            <div class="title">
                                <h1>Links Rápidos</h1>
                            </div>
                            <div class="link-footer">
                                <a href="">Incio</a>
                                <a href="">Produtos</a>
                                <a href="">Categoria</a>
                                <a href="">Sobre nós</a>
                            </div>
                        </div>
                        <div class="col3">
                            <div class="title">
                                <h1>Contato</h1>
                            </div>
                            <div class="contatos">
                                <div class="email">
                                    <p>Email: contato@techstore.com</p>
                                </div>
                                <div class="telefone">
                                    <p>Telefone: (XX) XXXX-XXXX</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="barra"></div>
                    <div class="rotulo">
                        <p>© 2025 TechStore. Todos os direitos reservados.</p>
                    </div>
                </footer>
            </body>

</html>
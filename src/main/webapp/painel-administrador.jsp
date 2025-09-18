<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/list-usuario.css?00000">
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
    <section>
        <div class="navbar">
            <div>
                <div>
                    <a href="./painel-administrador">Usuario</a>
                </div>
                <div>
                    <a href="./list-produto.jsp">Produto</a>
                </div>
            </div>
        </div>
        <div class="barra"></div>
        <div class="dashboard-container">
            <div class="content-area">
                <div class="content-header">
                    <h1>Usuarios</h1>
                    <a href="usuario?action=incluir" class="btn btn-primary">Novo Usuário
                    </a>
                    <a href="produto?action=listar" class="btn btn-primary" style="margin-left: 10px;">Listar Produtos</a>
                </div>
                <form method="get" action="usuario">
                    <input type="hidden" name="action" value="listar" />
                    <input type="text" name="filtroNome" placeholder="Filtrar por nome" value="${filtroNome}" />
                    <button type="submit">Filtrar</button>
                </form>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Status</th>
                                <th>Grupo</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td>${usuario.nome}</td>
                                    <td>${usuario.email}</td>
                                    <td>${usuario.status ? 'Ativo' : 'Inativo'}</td>
                                    <td>${usuario.nomeGrupo}</td>
                                    <td>
                                        <a href="usuario?action=alterarForm&id=${usuario.id}" class="button">Alterar</a>
                                        <a href="usuario?action=trocarStatus&id=${usuario.id}"
                                            class="button ${usuario.status ? 'danger' : ''}">
                                            ${usuario.status ? 'Inativar' : 'Ativar'}
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
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
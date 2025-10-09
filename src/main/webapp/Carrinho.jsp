<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <c:choose>
                <c:when test="${empty carrinho}">
                    <div class="carrinho-vazio">
                        <h2>Carrinho vazio</h2>
                        <p>Adicione produtos ao seu carrinho para continuar.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${carrinho}">
                        <div class="card-produto">
                            <div class="img">
                                <c:choose>
                                    <c:when test="${not empty item.produto.imagemPrincipal}">
                                        <img src="../imagens/${item.produto.imagemPrincipal.nomeArquivo}" alt="${item.produto.nome}">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="closeup-tiro-de-uma-linda-borboleta-com-texturas-interessantes-em-uma-flor-de-petalas-de-laranja_181624-7640.jpg" alt="Imagem padrão">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="titulo-produto">
                                <div>
                                    <h1>${item.produto.nome}</h1>
                                </div>
                            </div>
                            <div class="contador">
                                <div class="quantidade-controls">
                                    <button onclick="alterarQuantidade(${item.produto.id}, 'diminuir')" class="btn-quantidade">-</button>
                                    <span class="quantidade">${item.quantidade}</span>
                                    <button onclick="alterarQuantidade(${item.produto.id}, 'aumentar')" class="btn-quantidade">+</button>
                                </div>
                                <button onclick="removerProduto(${item.produto.id})" class="btn-remover">Remover</button>
                            </div>
                            <div class="preco">
                                <div>
                                    <div><p>Preço unitário:</p></div>
                                    <div><h2>R$ <fmt:formatNumber value="${item.produto.preco}" pattern="#,##0.00" /></h2></div>
                                    <div><p>Subtotal:</p></div>
                                    <div><h3>R$ <fmt:formatNumber value="${item.subtotal}" pattern="#,##0.00" /></h3></div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="resumo">
            <div class="card1">
                <div class="valor-produto">
                    <p class="description">Subtotal:</p>
                    <p class="valor">R$ <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" /></p>
                </div>
                
                <!-- Cálculo de Frete -->
                <div class="frete-section">
                    <h3>Calcular Frete</h3>
                    <form method="post" action="carrinho" onsubmit="calcularFrete(event)">
                        <input type="hidden" name="action" value="calcularFrete">
                        <div class="frete-options">
                            <label>
                                <input type="radio" name="frete" value="economico" onchange="atualizarFrete()" ${frete == 15.00 ? 'checked' : ''}>
                                Econômico - R$ 15,00 (5-7 dias)
                            </label>
                            <label>
                                <input type="radio" name="frete" value="normal" onchange="atualizarFrete()" ${frete == 25.00 ? 'checked' : ''}>
                                Normal - R$ 25,00 (3-5 dias)
                            </label>
                            <label>
                                <input type="radio" name="frete" value="expresso" onchange="atualizarFrete()" ${frete == 35.00 ? 'checked' : ''}>
                                Expresso - R$ 35,00 (1-2 dias)
                            </label>
                        </div>
                    </form>
                </div>
                
                <div class="frete-valor">
                    <p class="description">Frete:</p>
                    <p class="valor">R$ <fmt:formatNumber value="${frete}" pattern="#,##0.00" /></p>
                </div>
                
                <hr>
                <div class="total-produto">
                    <p class="description">Total a pagar:</p>
                    <p class="total">R$ <fmt:formatNumber value="${total}" pattern="#,##0.00" /></p>
                </div>
            </div>
            <div class="cardbutton">
                <div>
                    <div>
                        <a href="">Finalizar Compra</a>
                    </div>
                    <div>
                        <a href="index.jsp">Continuar Comprando</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

                <%@ include file=".././Components/footer.jsp" %>
                
                <script>
                    function alterarQuantidade(idProduto, acao) {
                        const form = document.createElement('form');
                        form.method = 'POST';
                        form.action = 'carrinho';
                        
                        const actionInput = document.createElement('input');
                        actionInput.type = 'hidden';
                        actionInput.name = 'action';
                        actionInput.value = acao;
                        
                        const idInput = document.createElement('input');
                        idInput.type = 'hidden';
                        idInput.name = 'idProduto';
                        idInput.value = idProduto;
                        
                        form.appendChild(actionInput);
                        form.appendChild(idInput);
                        document.body.appendChild(form);
                        form.submit();
                    }
                    
                    function removerProduto(idProduto) {
                        if (confirm('Tem certeza que deseja remover este produto do carrinho?')) {
                            const form = document.createElement('form');
                            form.method = 'POST';
                            form.action = 'carrinho';
                            
                            const actionInput = document.createElement('input');
                            actionInput.type = 'hidden';
                            actionInput.name = 'action';
                            actionInput.value = 'remover';
                            
                            const idInput = document.createElement('input');
                            idInput.type = 'hidden';
                            idInput.name = 'idProduto';
                            idInput.value = idProduto;
                            
                            form.appendChild(actionInput);
                            form.appendChild(idInput);
                            document.body.appendChild(form);
                            form.submit();
                        }
                    }
                    
                    function calcularFrete(event) {
                        event.preventDefault();
                        const form = event.target;
                        form.submit();
                    }
                    
                    function atualizarFrete() {
                        const freteSelecionado = document.querySelector('input[name="frete"]:checked');
                        if (freteSelecionado) {
                            console.log('Frete selecionado:', freteSelecionado.value);
                            
                            // Criar um formulário dinâmico para enviar a seleção de frete
                            const form = document.createElement('form');
                            form.method = 'POST';
                            form.action = 'carrinho';
                            
                            const actionInput = document.createElement('input');
                            actionInput.type = 'hidden';
                            actionInput.name = 'action';
                            actionInput.value = 'calcularFrete';
                            
                            const freteInput = document.createElement('input');
                            freteInput.type = 'hidden';
                            freteInput.name = 'frete';
                            freteInput.value = freteSelecionado.value;
                            
                            form.appendChild(actionInput);
                            form.appendChild(freteInput);
                            document.body.appendChild(form);
                            form.submit();
                        }
                    }
                </script>
            </body>

</html>
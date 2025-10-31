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
    if (!confirm('Tem certeza que deseja remover este produto do carrinho?')) return;

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

function calcularFrete() {
    const freteSelecionado = document.querySelector('input[name="frete"]:checked');

    if (!freteSelecionado) {
        alert("Selecione o frete antes de continuar!");
        return false;
    }

    const form = document.querySelector('form[action="checkout"]');
    if (form) form.submit();

    return true;
}

function atualizarFrete() {
    const freteSelecionado = document.querySelector('input[name="frete"]:checked');
    if (!freteSelecionado) return;

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
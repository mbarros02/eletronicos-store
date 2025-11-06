const radiosPagamento = document.querySelectorAll('input[name="formaPagamento"]');
    const cartaoInfo = document.getElementById('cartao-info');
    const form = document.querySelector('form');

    radiosPagamento.forEach(radio => {
        radio.addEventListener('change', () => {
            cartaoInfo.style.display = (radio.value === 'Cartao') ? 'block' : 'none';
        });
    });

    form.addEventListener('submit', function (event) {
        const formaPagamento = document.querySelector('input[name="formaPagamento"]:checked');
        if (!formaPagamento) {
            alert('Selecione uma forma de pagamento.');
            event.preventDefault();
            return;
        }

        if (formaPagamento.value === 'cartao') {
            const numero = document.querySelector('input[name="numeroCartao"]').value.trim();
            const nome = document.querySelector('input[name="nomeCartao"]').value.trim();
            const cvv = document.querySelector('input[name="codigoVerificador"]').value.trim();
            const vencimento = document.querySelector('input[name="dataVencimento"]').value.trim();
            const parcelas = document.querySelector('select[name="parcelas"]').value.trim();

            if (!numero || !nome || !cvv || !vencimento || !parcelas) {
                alert('Preencha todos os dados do cartão antes de continuar.');
                event.preventDefault();
            }
        }
    });
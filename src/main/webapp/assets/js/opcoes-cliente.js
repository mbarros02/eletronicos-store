const btnCliente = document.getElementById('btnCliente');
  const opcoesCliente = document.getElementById('opcoesCliente');

  btnCliente.addEventListener('click', () => {
    if(opcoesCliente.style.display === 'none' || opcoesCliente.style.display === '') {
      opcoesCliente.style.display = 'block';
    } else {
      opcoesCliente.style.display = 'none';
    }
  });
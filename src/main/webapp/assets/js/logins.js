const btnLogin = document.getElementById('btnLogin');
  const opcoesLogin = document.getElementById('opcoesLogin');

  btnLogin.addEventListener('click', () => {
    if(opcoesLogin.style.display === 'none' || opcoesLogin.style.display === '') {
      opcoesLogin.style.display = 'block';
    } else {
      opcoesLogin.style.display = 'none';
    }
  });
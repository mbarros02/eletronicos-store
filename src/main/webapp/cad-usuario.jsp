<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <title>Cadastro</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .form-container {
      background-color: white;
      padding: 20px 30px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="number"] {
      width: 100%;
      padding: 8px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    input[type="submit"] {
      width: 100%;
      background-color: #4CAF50;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }

    .mensagem {
      color: green;
      text-align: center;
    }
  </style>
</head>

<body>

  <div class="form-container">
    <h2>Cadastro de Usuarios</h2>
    <form action="cadastro" method="post">
      <input type="text" name="nome" placeholder="Nome" required>
      <input type="text" name="cpf" placeholder="CPF" required>
      <input type="email" name="email" placeholder="E-mail" required>
      <input type="password" name="senha" placeholder="Senha" required>
      <input type="number" name="idGrupo" placeholder="Id Grupo de Usuarios" required>
      <input type="submit" value="Cadastrar">
    </form>
  </div>

</body>

</html>
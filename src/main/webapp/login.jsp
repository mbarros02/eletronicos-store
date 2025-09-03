<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <title>Login</title>
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

    input[type="email"],
    input[type="password"] {
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
      color: red;
      text-align: center;
    }
  </style>
</head>

<body>

  <div class="form-container">
    <h2>Login</h2>
    <form action="login" method="post">
      <input type="hidden" name="action" value="login">
      <input type="email" name="email" placeholder="E-mail" required>
      <input type="password" name="senha" placeholder="Senha" required>
      <input type="submit" value="Entrar">
    </form>
    <div class="mensagem">
      <% if (request.getAttribute("erro") != null) { %>
        <p><%= request.getAttribute("erro") %></p>
      <% } %>
    </div>
  </div>

</body>

</html>

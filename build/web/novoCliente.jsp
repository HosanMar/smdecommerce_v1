<%-- 
    Document   : novoCliente
    Created on : 28 de out. de 2025, 17:01:36
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/partials/head.jsp" />
        <link rel="stylesheet" href="assets/style.css">
    </head>
    <body>
        <div class="registration-form">
            <form action="InserirCliente" method="post">
              <div class="mb-3">
                <label for="exampleInputName" class="form-label">Nome</label>
                <input type="text" name="nome" class="form-control" id="exampleInputName">
              </div>
              <div class="mb-3">
                <label for="exampleInputAdress" class="form-label">Endereço</label>
                <input type="text" name="endereco" class="form-control" id="exampleInputAdress">
              </div>
              <div class="mb-3">
                <label for="exampleInputEmail" class="form-label">Email</label>
                <input type="email" name="email" class="form-control" id="exampleInputEmail" aria-describedby="emailHelp">
              </div>
              <div class="mb-3">
                <label for="exampleInputLogin" class="form-label">Login</label>
                <input type="text" name="login" class="form-control" id="exampleInputLogin">
              </div>
              <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Senha</label>
                <input type="password" name="senha" class="form-control" id="exampleInputSenha">
              </div>  
              <button type="submit" class="btn btn-block create-account">Salvar</button>
              <div class="form-actions"  
                <a> Já tem cadastro? </a>
                <a href="index.jsp">Faça o login</a>
              </div>
  
              <% if (request.getAttribute("mensagem") != null) {%>
                  <a><%= request.getAttribute("mensagem")%></a>
                  <% }%>
 
            </form>
 
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
    </body>
</html>

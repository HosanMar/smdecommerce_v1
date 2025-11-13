<%-- 
    Document   : index
    Created on : 30 de out. de 2025, 17:06:49
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<!--        <title>smd e-commerce</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
        <jsp:include page="/partials/head.jsp" />
        <link rel="stylesheet" href="assets/style.css">
    </head>
    <body>
        <div class="registration-form">
            <form action="Login" method="post">  
<!--        Login: <input type="text" name="login" /><br/>
            Senha: <input type="password" name="senha" /><br/>
            <input type="submit" value="Entrar"/>-->
            <div class="mb-3">
                <label for="exampleInputLogin" class="form-label">Login</label>
                <input type="text" name="login" class="form-control" id="exampleInputLogin">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Senha</label>
                <input type="password" name="senha" class="form-control" id="exampleInputSenha">
            </div>  
            <button type="submit" class="btn btn-primary">Entrar</button>
            <a>Ainda não tem conta?</a>
            <a href="novoCliente.jsp">Fazer cadastro</a>
            <% if (request.getAttribute("mensagem") != null) {%>
            <a><%= request.getAttribute("mensagem")%></a>
            <% }%>
            </form>
        </div>
    </body>
</html>

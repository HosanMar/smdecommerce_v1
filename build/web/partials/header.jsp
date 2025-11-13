<%-- 
    Document   : header
    Created on : Nov 13, 2025, 12:48:48 PM
    Author     : gal
--%>
<%@page import="model.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("usuario") != null) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <header class="border-bottom">

      <!-- Linha superior (navbar) -->
      <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand fw-bold" href="#">Logo</a>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form> 
            <div>
                <a href="#" class="text-muted me-2">Olá, <%=usuario.getNome()%></a>
                <a href="atualizarUsuario.jsp" class="text-muted">Dados pessoais</a>
                <a href="Logout" class="text-muted">Sair</a>
            </div>
        </div>
      </nav>
<%--       
<!--        <h1>Olá, <%= usuario.getNome()%></h1>
        <h3>Você é um <%= usuario.getAdministrador() ? "Administrador" : "Cliente" %></h3>
        <a href="RemoverUsuario?id=<%= usuario.getId()%>">Remover Conta</a>
        <a href="atualizarUsuario.jsp">Atualizar Conta</a>
        <a href="Logout">Sair</a>-->
            --%>
        <!-- Linha inferior -->
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Categorias
                </a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Hardware</a></li>
                    <li><a class="dropdown-item" href="#">Computadores</a></li>
                    <li><a class="dropdown-item" href="#">Periféricos</a></li>
                    <li><a class="dropdown-item" href="#">PC Gamer</a></li>
                    <li><a class="dropdown-item" href="#">Escritório</a></li>
                    <li><a class="dropdown-item" href="#">Monitores</a></li>
                    <li><a class="dropdown-item" href="#">Smartphones</a></li>
                    <li><a class="dropdown-item" href="#">Áudio</a></li>
                    <li><a class="dropdown-item" href="#">Consoles</a></li>
                    <li><a class="dropdown-item" href="#">Energia</a></li>
                </ul>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Hardware</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Computadores</a>
                </li>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Periféricos</a>
                </li>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">PC Gamer</a>
                </li>
                <button class="btn btn-outline-secondary btn-sm"><i class="bi bi-cart2"></i></button>
            </ul>
            </div>


        </div>
        </nav>


    </header>




<!--    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>-->
</html>

<%
    } else {
        request.setAttribute("mensagem", "Você não tem permissão para acessar este recurso");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
%>

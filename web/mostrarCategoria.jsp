<%-- 
    Document   : mostrarCategoria
    Created on : Dec 11, 2025, 10:35:53 AM
    Author     : gal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.categoria.Categoria"%>
<%@page import="java.util.List"%>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/head.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="assets/style.css">
    </head>
    <body>
        <h3>Atualizar uma Categoria Existente</h3>
        <%
            Categoria categoria = (Categoria) request.getAttribute("categoria");
            
            if (categoria != null) {
        %>
        <form action="AtualizarCategoria">
            <input type="hidden" name="id" value="<%= categoria.getId() %>" />
            <input type="text" name="nome" placeholder="Entre com o nome da categoria" value="<%= categoria.getNome() %>" /><br/>
            <input type="submit" value="Atualizar"/>
        </form>
        
        <%
            } else {
        %>
        <div>Não foi possível localizar a categoria</div>
        <%
            }
        %>    
        
        <a href="ListarCategoria">Voltar</a>
    </body>
</html>

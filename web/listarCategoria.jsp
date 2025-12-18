<%-- 
    Document   : ListarCategoria
    Created on : Dec 11, 2025, 10:21:11?AM
    Author     : gal
--%>

<%@page import="model.categoria.Categoria"%>
<%@page import="model.categoria.CategoriaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/partials/head.jsp" />
<jsp:include page="/partials/headerAdmin.jsp" />

<!DOCTYPE html>
<html>
    <head>    
        <link rel="stylesheet" href="assets/style.css">
    </head>
    <body>
        
        <h1>Cadastro de Categorias</h1>
        <h3>Cadastro de uma Nova Categoria</h3>
        <form action="${pageContext.request.contextPath}/InserirCategoria" method="post">
            <input type="text" name="nome" placeholder="Entre com o nome da categoria" /><br/>
                <input type="submit" value="Inserir"/>
        </form>
        <h3>Listagem de Categorias</h3>
        <%
//            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.obterTodas();
            request.setAttribute("categorias", categorias);
            if (categorias != null && !categorias.isEmpty()) {
        %>
        <table>
            <tr>
                <td>Id</td>
                <td>Nome</td>
                <td>&nbsp;</td>
            </tr>
            <%
                for (Categoria c : categorias) {
            %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getNome() %></td>
                <td><a href="MostrarCategoria?id=<%= c.getId() %>">Atualizar</a>|<a href="RemoverCategoria?id=<%= c.getId() %>">Remover</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <div>Não existem categorias registradas</div>
        <%
            }
        %>
        <a href="ListarCategoria">Atualizar Registros</a>
        <%@include file="/partials/footer.jsp" %>

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
    </body>
</html>

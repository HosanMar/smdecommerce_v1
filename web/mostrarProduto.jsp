<%-- 
    Document   : mostrarProduto
    Created on : Dec 11, 2025, 10:36:07 AM
    Author     : gal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.categoria.Categoria"%>
<%@page import="model.produto.Produto"%>
<%@page import="java.util.List"%>
<jsp:include page="/partials/header.jsp" />
<jsp:include page="/partials/head.jsp" />

<!DOCTYPE html>
<html>
    <body>
        <head>
            <link rel="stylesheet" href="assets/style.css">
        </head>
        
       <h3>Atualizar um Produto Existente</h3>
        <%
            Produto produto = (Produto) request.getAttribute("produto");
        %>
        <form action="AtualizarProduto"  method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= produto.getId() %>" />
            <input type="text" name="descricao" placeholder="Entre com a descrição do produto" value="<%= produto.getDescricao() %>" /><br/>
            <input type="text" name="preco" placeholder="Entre com o preço do produto" value="<%= produto.getPreco() %>" /><br/>
            <input type="file" name="foto" placeholder="Entre com a foto do produto" /><br/>
            <input type="text" name="quantidade" placeholder="Entre com o quantidade do produto" value="<%= produto.getQuantidade() %>" /><br/>
            <%
              List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            %>
            <select name="categoriaId">
                <option value="">Selecione...</option>
                <% for (Categoria c : categorias) { %>
                    <% if (c.getId().intValue() == produto.getCategoria().getId().intValue()) { %>
                    <option selected="true" value="<%= c.getId() %>"><%= c.getNome() %></option>    
                    <% } else { %>
                <option value="<%= c.getId() %>"><%= c.getNome() %></option>
                    <% } %>
                <% } %>
            </select>
            <input type="submit" value="Atualizar" enctype="multipart/form-data"/>
        </form>
        <a href="ListarProduto">Voltar</a>
    </body>
</html>

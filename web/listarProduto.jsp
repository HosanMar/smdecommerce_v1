<%-- 
    Document   : listarProduto
    Created on : Dec 11, 2025, 10:30:40 AM
    Author     : gal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.categoria.Categoria"%>
<%@page import="model.categoria.CategoriaDAO"%>
<%@page import="model.produto.Produto"%>
<%@page import="model.produto.ProdutoDAO"%>
<%@page import="java.util.List"%>
<jsp:include page="/partials/head.jsp" />
<jsp:include page="/partials/headerAdmin.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="assets/style.css">
    </head>
    <body>
        <h1>Cadastro de Produtos</h1>
        <h3>Cadastro de um Novo Produto</h3>
        <form action="InserirProduto" method="post" enctype="multipart/form-data">
            <input type="text" name="descricao" placeholder="Entre com o descrição do produto" /><br/>
            <input type="text" name="preco" placeholder="Entre com o preço do produto" /><br/>
            <input type="file" name="foto" placeholder="Entre com a foto do produto" /><br/>
            <input type="text" name="quantidade" placeholder="Entre com o quantidade do produto" /><br/>
            <%
//          List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.obterTodas();
            request.setAttribute("categorias", categorias);
            if (categorias != null && !categorias.isEmpty()) {
            %>
            <select name="categoriaId">
                <option value="">Selecione...</option>
                <% for (Categoria c : categorias) { %>
                <option value="<%= c.getId() %>"><%= c.getNome() %></option>
                <% } %>
            </select>
            <br/>
            <input type="submit" value="Inserir"/>
        </form>
        <h3>Listagem de Produtos</h3>
        <%
//           List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = produtoDAO.obterTodos();
            request.setAttribute("produtos", produtos);
            if (produtos != null && !produtos.isEmpty()) {
        %>
        <table>
            <tr>
                
                <td>Id</td>
                <td>Descrição</td>
                <td>Preço</td>
                <td>Quantidade</td> 
                <td>Categoria</td>
                <td>&nbsp;</td>
            </tr>
            <%
                for (Produto p : produtos) {
            %>
            <tr>
                <td><%= p.getFoto() %></td>
                <td><%= p.getId() %></td>
                <td><%= p.getDescricao() %></td>
                <td><%= p.getPreco()%></td>
                <td><%= p.getQuantidade()%></td>
                <td><%= p.getCategoria().getNome() %></td>
                <td><a href="MostrarProduto?id=<%= p.getId() %>">Atualizar</a>|<a href="RemoverProduto?id=<%= p.getId() %>">Remover</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <div>Não existem produtos registrados</div>
        <%
            }
        %>

        <%
            } else {
        %>
        <div>Não existem categorias registradas</div>
        <%
            }
        %>
        <a href="ListarProduto">Atualizar Registros</a>
    </body>
</html>

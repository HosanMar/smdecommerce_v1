<%-- 
    Document   : principal
    Created on : 30 de out. de 2025, 17:20:03
    Author     : Leonardo Oliveira Moreira
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.produto.Produto" %>
<%@ page import="model.produto.ProdutoDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/partials/head.jsp" />
    </head>
    
    <body>
        <header>
            <jsp:include page="/partials/header.jsp" />
        </header>
        
        <div class="container my-4">
            <div class="row">

        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");

            if (produtos != null && !produtos.isEmpty()) {
                for (Produto p : produtos) {
        %>
                <div class="col-md-4 mb-4">
                    <div class="card product-card h-100">

                        <img src="MostrarImagemProduto?id=<%= p.getId() %>"
                             class="card-img-top"
                             alt="<%= p.getDescricao() %>">


                        <div class="card-body">
                            <h5 class="card-title product-title">
                                <%= p.getDescricao() %>
                            </h5>

                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <span class="product-price">
                                    R$ <%= String.format("%.2f", p.getPreco()) %>
                                </span>
                            </div>

                            <div class="card-footer d-flex justify-content-between">
                                <button class="btn btn-primary btn-sm">
                                    Add to Cart
                                </button>

                                <button class="btn btn-outline-secondary btn-sm">
                                    <i class="bi bi-heart"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
        <%
                }
            } else {
        %>
                <div class="col-12">
                    <p class="text-muted">Nenhum produto encontrado.</p>
                </div>
        <%
            }
        %>

            </div>
        </div>

          
        
    </body>
</html>


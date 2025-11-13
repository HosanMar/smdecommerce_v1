/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.produto;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_SENHA;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USUARIO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author gal
 */
public class ProdutoDAO {
  public ProdutoDAO() {
   }

   public List<Produto> obterTodas() {
      List<Produto> resultado = new ArrayList();

      try {
         Class.forName(JDBC_DRIVER);
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT id, nome, preco, quantidade, imagem FROM produtos");

         while(resultSet.next()) {
            Produto produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            resultado.add(produto);
         }

         resultSet.close();
         statement.close();
         connection.close();
         return resultado;
      } catch (SQLException | ClassNotFoundException var6) {
         return null;
      }
   }

   public Produto obter(int id) {
      Produto produto = null;

      try {
         Class.forName(JDBC_DRIVER);
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco FROM produtos WHERE id = ?");
         preparedStatement.setInt(1, id);
         ResultSet resultSet = preparedStatement.executeQuery();

         while(resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
         }

         resultSet.close();
         preparedStatement.close();
         connection.close();
         return produto;
      } catch (SQLException | ClassNotFoundException var6) {
         return null;
      }
   }

   public boolean inserir(String descricao, Double preco) {
      boolean sucesso = false;

      try {
         Class.forName(JDBC_DRIVER);
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (descricao, preco) VALUES (?, ?)");
         preparedStatement.setString(1, descricao);
         preparedStatement.setDouble(2, preco);
         sucesso = preparedStatement.executeUpdate() == 1;
         preparedStatement.close();
         connection.close();
         return sucesso;
      } catch (SQLException | ClassNotFoundException var6) {
         return false;
      }
   }

   public boolean atualizar(String descricao, double preco, int id) {
      boolean sucesso = false;

      try {
         Class.forName(JDBC_DRIVER);
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET descricao = ?, preco = ? WHERE id = ?");
         preparedStatement.setString(1, descricao);
         preparedStatement.setDouble(2, preco);
         preparedStatement.setInt(3, id);
         sucesso = preparedStatement.executeUpdate() == 1;
         preparedStatement.close();
         connection.close();
         return sucesso;
      } catch (SQLException | ClassNotFoundException var8) {
         return false;
      }
   }

   public boolean remover(int id) {
      boolean sucesso = false;

      try {
         Class.forName(JDBC_DRIVER);
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produtos WHERE id = ?");
         preparedStatement.setInt(1, id);
         sucesso = preparedStatement.executeUpdate() == 1;
         preparedStatement.close();
         connection.close();
         return sucesso;
      } catch (SQLException | ClassNotFoundException var5) {
         return false;
      }
   }
}

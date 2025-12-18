package model.produto;

import static config.Config.JDBC_DRIVER;
import static config.Config.JDBC_SENHA;
import static config.Config.JDBC_URL;
import static config.Config.JDBC_USUARIO;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;

/**
 *
 * @author gal
 */
public class ProdutoDAO {

   /**
     * Método utilizado para obter todos os produtos existentes
     *
     * @return
     */
    public List<Produto> obterTodos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto ORDER BY descricao ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return produtos;
    }

   
   /**
     * Método utilizado para obter todos os produtos em estoque
     *
     * @return
     */
    public List<Produto> obterTodosEmEstoque() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto WHERE quantidade > 0 ORDER BY descricao ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return produtos;
    }
    
    /**
     * Método utilizado para obter um produto pelo identificador
     *
     * @param id
     * @return
     */
    
    public Produto obter(int id) {
        Produto produto = null;

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(
                JDBC_URL, JDBC_USUARIO, JDBC_SENHA
            );

            PreparedStatement ps = connection.prepareStatement(
                "SELECT id, descricao, preco, foto, quantidade, categoria_id " +
                "FROM produto WHERE id = ?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setFoto(rs.getString("foto"));
                produto.setQuantidade(rs.getInt("quantidade"));

                Categoria categoria = categoriaDAO.obter(rs.getInt("categoria_id"));
                produto.setCategoria(categoria);
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        return produto;
    }

    
//   public Produto obter(int id) {
//      Produto produto = null;
//
//      try {
//         Class.forName(JDBC_DRIVER);
//         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
//         PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco FROM produto WHERE id = ?");
//         preparedStatement.setInt(1, id);
//         ResultSet resultSet = preparedStatement.executeQuery();
//
//         while(resultSet.next()) {
//            produto = new Produto();
//            produto.setId(resultSet.getInt("id"));
//            produto.setDescricao(resultSet.getString("descricao"));
//            produto.setPreco(resultSet.getDouble("preco"));
//         }
//
//         resultSet.close();
//         preparedStatement.close();
//         connection.close();
//         return produto;
//      } catch (SQLException | ClassNotFoundException var6) {
//         return null;
//      }
//   }
    
    
    /**
     * Método para inserir um novo produto
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @return
     */
   public boolean inserir(String descricao, double preco, Part foto, int quantidade, int categoriaId) {
      boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (descricao, preco, foto, quantidade, categoria_id) VALUES (?, ?, NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setInt(4, categoriaId);
            sucesso = (preparedStatement.executeUpdate() == 1);
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long produtoId = 0;
            if (resultSet.next()) {
                produtoId = resultSet.getLong(1);
            }
            resultSet.close();
            preparedStatement.close();
            if (sucesso && foto != null) {
                String fileName = foto.getSubmittedFileName();
                fileName = produtoId + fileName.substring(fileName.lastIndexOf("."));
                File uploadDir = new File("/home/gal/Documents/upload/");
                File file = new File(uploadDir, fileName);
                try (InputStream input = foto.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                Statement statement = connection.createStatement();
                sucesso = statement.executeUpdate("UPDATE produto SET foto = '" + file.getName() + "' WHERE id = " + produtoId) == 1;
                statement.close();
            }
            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return sucesso;
    }

   /**
     * Método para atualizar um produto existente
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @param id
     * @return
     */
    public boolean atualizar(String descricao, double preco, Part foto, int quantidade, int categoriaId, int id) {
        boolean sucesso = false;
        Produto produto = obter(id);
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, foto = NULL, quantidade = ?, categoria_id = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setInt(4, categoriaId);
            preparedStatement.setInt(5, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            if (sucesso && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                File uploadDir = new File("/home/gal/Documents/upload/");
                File file = new File(uploadDir, produto.getFoto());
                if (file.exists()) {
                    file.delete();
                }
            }
            if (sucesso && foto != null) {
                String fileName = foto.getSubmittedFileName();
                fileName = id + fileName.substring(fileName.lastIndexOf("."));
                File uploadDir = new File("/home/gal/Documents/upload/");
                File file = new File(uploadDir, fileName);
                try (InputStream input = foto.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                Statement statement = connection.createStatement();
                sucesso = statement.executeUpdate("UPDATE produto SET foto = '" + file.getName() + "' WHERE id = " + id) == 1;
                statement.close();
            }
            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return sucesso;
    }

   /**
     * Método para remover um produto existente
     *
     * @param id
     * @return
     */
    
    public boolean remover(int id) {
        boolean sucesso;
        Produto produto = obter(id);

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM produto WHERE id = ?"
            );
            ps.setInt(1, id);

            sucesso = ps.executeUpdate() == 1;

            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }

            ps.close();
            connection.close();

            // Arquivo é responsabilidade secundária
            if (sucesso && produto != null && produto.getFoto() != null && !produto.getFoto().isBlank()) {
                File file = new File("/home/gal/Documents/upload/", produto.getFoto());
                if (!file.delete()) {
                    System.err.println("Imagem não removida: " + file.getAbsolutePath());
                }
            }

            return sucesso;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
//    public boolean remover(int id) {
//        boolean sucesso = false;
//        Produto produto = obter(id);
//        try {
//            Class.forName(JDBC_DRIVER);
//            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
//            connection.setAutoCommit(false);
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            sucesso = (preparedStatement.executeUpdate() == 1);
//            preparedStatement.close();
//            if (sucesso && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
//                File uploadDir = new File("/home/gal/Documents/upload/");
//                File file = new File(uploadDir, produto.getFoto());
//                sucesso = file.delete();
//            }
//            if (sucesso) {
//                connection.commit();
//            } else {
//                connection.rollback();
//            }
//            connection.close();
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return sucesso;
//    }
}

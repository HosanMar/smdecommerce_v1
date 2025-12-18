package model.categoria;

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
public class CategoriaDAO {
        /**
     * Método utilizado para obter todas as categorias existentes
     *
     * @return
     */
    public List<Categoria> obterTodas() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome FROM categoria");
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                resultado.add(categoria);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return resultado;
    }

    /**
     * Método utilizado para obter uma categoria existente
     *
     * @param id
     * @return
     */
    public Categoria obter(int id) {
        Categoria categoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome FROM categoria WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return categoria;
    }

    /**
     * Método utilizado para inserir uma nova categoria
     *
     * @param nome
     * @return
     */
    public boolean inserir(String nome) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (nome) VALUES (?)");
            preparedStatement.setString(1, nome);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }
    

    /**
     * Método utilizado para atualizar uma categoria existente
     *
     * @param nome
     * @param id
     * @return
     */
    public boolean atualizar(String nome, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET nome = ? WHERE id = ?");
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

    /**
     * Método utilizado para remover uma categoria existente
     *
     * @param id
     * @return
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?");
            preparedStatement.setInt(1, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

}
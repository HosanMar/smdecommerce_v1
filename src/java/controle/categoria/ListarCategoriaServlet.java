package controle.categoria;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;

/**
 *
 * @author gal
 */

@WebServlet("/ListarCategoria")
public class ListarCategoriaServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obterTodas();
        
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("listarCategoria.jsp").forward(request, response);
    }
}

package controle.categoria;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;


/**
 *
 * @author gal
 */

@WebServlet("/MostrarCategoria")
public class MostrarCategoriaServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = categoriaDAO.obter(id);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("mostrarCategoria.jsp").forward(request, response);
    }
}

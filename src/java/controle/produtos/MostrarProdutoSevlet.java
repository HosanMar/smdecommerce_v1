package controle.produtos;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.produto.Produto;
import model.produto.ProdutoDAO;

/**
 *
 * @author gal
 */
@MultipartConfig
@WebServlet(name = "MostrarProdutoSevlet", urlPatterns = {"/MostrarProduto"})
public class MostrarProdutoSevlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.obter(id);
        request.setAttribute("produto", produto);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obterTodas();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("mostrarProduto.jsp").forward(request, response);
    }


}

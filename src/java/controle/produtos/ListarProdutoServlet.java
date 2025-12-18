package controle.produtos;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;

/**
 *
 * @author gal
 */
@WebServlet("/ListarProduto")
public class ListarProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.obterTodos();
        
        request.setAttribute("produtos", produtos);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        List<Categoria> categorias = categoriaDAO.obterTodas();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("listarProduto.jsp").forward(request, response);
    }
}

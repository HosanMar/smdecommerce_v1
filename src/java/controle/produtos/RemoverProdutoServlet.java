package controle.produtos;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.produto.ProdutoDAO;

/**
 *
 * @author gal
 */
@WebServlet("/RemoverProduto")
public class RemoverProdutoServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.remover(id);
        request.setAttribute("mensagem", sucesso ? "Registro removido com sucesso" : "Não foi possível remover o registro");
        response.sendRedirect("ListarProduto");

    }

}

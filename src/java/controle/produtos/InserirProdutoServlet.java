package controle.produtos;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.produto.ProdutoDAO;

/**
 *
 * @author gal
 */
@WebServlet(name = "InserirProdutoServlet", urlPatterns = {"/InserirProduto"})
@MultipartConfig
public class InserirProdutoServlet extends HttpServlet {

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
        
        String descricao = request.getParameter("descricao");
        
        double preco = Double.parseDouble(request.getParameter("preco"));
        
        Part foto = request.getPart("foto");
        
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.inserir(descricao, preco, foto, quantidade, categoriaId);
        
        request.setAttribute("mensagem", sucesso ? "Registro inserido com sucesso" : "Não foi possível inserir o registro");
        request.getRequestDispatcher("ListarProduto").forward(request, response);
    }

}

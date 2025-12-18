package controle.categoria;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.categoria.CategoriaDAO;

/**
 *
 * @author gal
 * 
 */

@WebServlet("/InserirCategoria")
public class InserirCategoriaServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = categoriaDAO.inserir(nome);
        
        
        request.getSession().setAttribute(
            "mensagem",
            sucesso ? "Registro inserido com sucesso"
                    : "Não foi possível inserir o registro"
        );

        response.sendRedirect(request.getContextPath() + "/ListarCategoria");
        
//        request.setAttribute("mensagem", sucesso ? "Registro inserido com sucesso" : "Não foi possível inserir o registro");
//        request.getRequestDispatcher("ListarCategoria").forward(request, response);
    }
    
}

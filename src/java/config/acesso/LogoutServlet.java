package config.acesso;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author gal
 */
public class LogoutServlet extends HttpServlet {
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("mensagem", "Sua sessão foi encerrada");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Inicio");
        requestDispatcher.forward(request, response);
    }
}

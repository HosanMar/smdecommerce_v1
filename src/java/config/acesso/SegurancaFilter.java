package config.acesso;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.usuario.Usuario;

/**
 *
 * @author gal
 */
public class SegurancaFilter implements Filter {
    public SegurancaFilter(){
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println(req.getRequestURI());
        if (req.getRequestURI().equals(req.getServletContext().getContextPath() + "/" + "principal.jsp") || req.getRequestURI().equals(req.getServletContext().getContextPath() + "/" + "atualizarUsuario.jsp")) {
            HttpSession session = req.getSession();
            if (session.getAttribute("usuario") != null && session.getAttribute("usuario") instanceof Usuario) {
                chain.doFilter(req, response);  
            } else {
                request.setAttribute("mensagem", "Você não tem permissão para acesso este recurso");
                request.getRequestDispatcher("Inicio").forward(request, response);
            }
        } else {
            chain.doFilter(req, response);    
        }
    }
}

package config.acesso;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.usuario.Usuario;

/**
*
* @author gal
* 
* 
* 
*/
@WebFilter(urlPatterns = {
    "/listarCategoria.jsp",
    "/listarProduto.jsp",
    "/principalAdmin.jsp",
    "/controle.produtos/*",
    "/controle.categoria/*"
})

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("usuario") instanceof Usuario) {

            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario.getAdministrador()) {
                chain.doFilter(request, response);
                return;
            }
        }

        request.setAttribute("mensagem", "Acesso restrito a administradores");
        request.getRequestDispatcher("Inicio")
               .forward(request, response);
    }
}





//package config.acesso;
//
//import java.io.IOException;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import model.usuario.Usuario;
//
///**
// *
// * @author gal
// * 
// */
//public class AdminFilter implements Filter {
//    public AdminFilter(){
//    }
//    
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain)
//            throws IOException, ServletException {    
//        
//        HttpServletRequest req = (HttpServletRequest) request;
//        System.out.println(req.getRequestURI());
//        
//        String uri = req.getRequestURI();
//        
//        // Páginas exclusivas de admin
//        if(uri.endsWith("listarCategoria.jsp") || uri.endsWith("listarProduto.jsp") || uri.endsWith("principalAdmin.jsp")){
//            
////        if (req.getRequestURI().equals(
////                req.getServletContext().getContextPath() + "/" + "principalAdmin.jsp")
////            || req.getRequestURI().equals(
////                req.getServletContext().getContextPath() + "/" + "listarProduto.jsp")) {
//
//            HttpSession session = req.getSession();
//            
//            if (session.getAttribute("usuario") != null
//                    && session.getAttribute("usuario") instanceof Usuario) {
//
//                Usuario usuario = (Usuario) session.getAttribute("usuario");
//                
//                // ===== VERIFICAÇÃO DE ADMIN =====
//                if (usuario.getAdministrador()) {
//                    chain.doFilter(req, response);
//                } else {
//                    request.setAttribute("mensagem",
//                            "Acesso restrito a administradores");
//                    request.getRequestDispatcher("Inicio")
//                           .forward(request, response);
//                }
//            } else {
//                request.setAttribute("mensagem",
//                        "Você precisa estar logado para acessar este recurso");
//                request.getRequestDispatcher("Inicio")
//                       .forward(request, response);
//            }   
//        } else {
//            chain.doFilter(req, response);
//        }
//    }
//}

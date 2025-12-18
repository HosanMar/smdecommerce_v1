package controle.usuario;

import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import model.usuario.Usuario;
import model.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de identificar um usuário
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        if (login == null || login.trim().isEmpty() ||
        senha == null || senha.trim().isEmpty()) {

        request.setAttribute("mensagem", "Preencha login e senha.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
        }
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.obterTodos();
        
        request.setAttribute("produtos", produtos);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obterTodas();
        request.setAttribute("categorias", categorias);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obter(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            Cookie c = new Cookie("smdecommerce.login", login);
            c.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(c);
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            if(usuario.getAdministrador()){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("principalAdmin.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("principal.jsp");
                requestDispatcher.forward(request, response);
            }
            
        }else {
            request.setAttribute("mensagem", "Login ou senha incorreta");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}

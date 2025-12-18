package controle.produtos;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import model.produto.Produto;
import model.produto.ProdutoDAO;

/**
 *
 * @author gal
 */
@WebServlet("/MostrarImagemProduto")
public class MostrarImagemProdutoServlet extends HttpServlet {

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
        
        String idParam = request.getParameter("id");
    if (idParam == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }

    int id = Integer.parseInt(idParam);

    ProdutoDAO produtoDAO = new ProdutoDAO();
    Produto produto = produtoDAO.obter(id);

    if (produto == null || produto.getFoto() == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
    }

    File foto = new File("/home/gal/Documents/upload/" + produto.getFoto());

    if (!foto.exists()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
    }

    String mimeType = getServletContext().getMimeType(foto.getName());
    response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
    response.setContentLengthLong(foto.length());

    try (FileInputStream fis = new FileInputStream(foto);
         OutputStream out = response.getOutputStream()) {

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
    }
        
        
//        int id = Integer.parseInt(request.getParameter("id"));
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        Produto produto = produtoDAO.obter(id);
//        File foto = new File("/home/gal/Documents/upload/" + produto.getFoto());
//        String mimeType = getServletContext().getMimeType(foto.getName());
//        response.setContentType(mimeType);
//        response.setContentLengthLong(foto.length());
//        try (FileInputStream fis = new FileInputStream(foto); OutputStream out = response.getOutputStream()) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = fis.read(buffer)) != -1) {
//                out.write(buffer, 0, bytesRead);
//            }
//        }
    }

}

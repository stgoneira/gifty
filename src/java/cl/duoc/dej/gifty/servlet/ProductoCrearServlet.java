package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.service.CategoriaService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
@WebServlet(name = "ProductoCrearServlet", urlPatterns = {"/crear"})
public class ProductoCrearServlet extends HttpServlet {

    protected void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categorias", new CategoriaService().getCategorias());
        request.getRequestDispatcher("/WEB-INF/jsp/producto/crear.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    
    
}

package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.service.CategoriaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaListarServlet", urlPatterns = {"/listar-categorias"})
public class CategoriaListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaService categoriaService = new CategoriaService();
        List<Categoria> listaCategorias = categoriaService.getCategorias();
        request.setAttribute("categorias", listaCategorias);
        request.getRequestDispatcher("/WEB-INF/jsp/categoria/listar.jsp").forward(request, response);
    }
    
}

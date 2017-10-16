package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.service.CategoriaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaCrearServlet", urlPatterns = {"/crear-categoria"})
public class CategoriaCrearServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/categoria/crear.jsp").forward(request, response);
    }
        
    protected void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCategoria = request.getParameter("categoria");
        Categoria categoria = new Categoria(nombreCategoria);
        CategoriaService categoriaService = new CategoriaService();
        Categoria resultado = categoriaService.crearCategoria(categoria);
        request.setAttribute("categoria", resultado);
        
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        
        if(resultado != null) {
            mensajes.add( String.format("Se ha creado correctamente la categoría %s con ID %s", resultado.getNombre(), resultado.getId().toString()) );
        } else {
            errores.add(String.format("No se ha podido crear la categoría: %s", categoria.getNombre()));
        }
        
        request.setAttribute("errores", errores);
        request.setAttribute("mensajes", mensajes);
        request.getRequestDispatcher("/WEB-INF/jsp/categoria/crear.jsp").forward(request, response);
    }

    

}

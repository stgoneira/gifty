package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.service.CategoriaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaEliminarServlet", urlPatterns = {"/CategoriaEliminarServlet"})
public class CategoriaEliminarServlet extends HttpServlet {

    private Logger logger;
    
    {
        logger = Logger.getLogger(getClass().getSimpleName());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = 0L;
        String stringId = request.getParameter("id");      
        String error = null;
        String mensaje = null;
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        
        if (stringId == null) {
            error = "No se ha especificado una categoría para eliminar.";
            errores.add(error);
            logger.log(Level.INFO, error);
            request.setAttribute("errores", errores);
            despachar(request, response);
            return;
        }
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException nfe) {
            error = "El valor ingresado no es un ID de categoría válido.";
            errores.add(error);
            logger.log(Level.INFO, error);
            request.setAttribute("errores", errores);
            despachar(request, response);
        }
        CategoriaService categoriaService = new CategoriaService();
        boolean resultado = categoriaService.eliminarCategoria(id);
        if(resultado) {
            mensaje = String.format("La categoría con ID %s fue eliminada correctamente", id);
            mensajes.add(mensaje);
            logger.log(Level.INFO, mensaje);
        } else {
            error = String.format("La categoría no pudo ser eliminada");
            errores.add(error);
            logger.log(Level.SEVERE, error);
        }
        
        request.setAttribute("categorias", categoriaService.getCategorias());
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
        despachar(request, response);
    }

    private void despachar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/categoria/listar.jsp").forward(request, response);
    }

}

package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.entity.Producto;
import cl.duoc.dej.gifty.service.CategoriaService;
import cl.duoc.dej.gifty.service.ProductoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaService categoriaService = new CategoriaService();
        request.setAttribute("categorias", new CategoriaService().getCategorias());
        request.getRequestDispatcher("/WEB-INF/jsp/producto/crear.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("producto");
        String stringCategoria = request.getParameter("categoria");
        String stringPrecio = request.getParameter("precio");
        String imagen = request.getParameter("imagen");

        Long categoriaId = Long.parseLong(stringCategoria);
        Long precio = Long.parseLong(stringPrecio);

        ProductoService productoService = new ProductoService();
        CategoriaService categoriaService = new CategoriaService();

        Categoria categoria = categoriaService.getCategoriaById(categoriaId);
        Producto producto = new Producto(nombre, precio, imagen, categoria);
        Producto resultado = productoService.crearProducto(producto);

        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();

        if (resultado != null) {
            mensajes.add(String.format("Se ha creado correctamente el producto %s con ID %s", resultado.getNombre(), resultado.getId().toString()));
        } else {
            errores.add(String.format("No se ha podido crear el producto: %s", categoria.getNombre()));
        }

        request.setAttribute("producto", resultado);

        request.setAttribute("errores", errores);
        request.setAttribute("mensajes", mensajes);

        request.setAttribute("categorias", new CategoriaService().getCategorias());

        request.getRequestDispatcher("/WEB-INF/jsp/producto/crear.jsp").forward(request, response);
    }


}

package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.entity.Producto;
import cl.duoc.dej.gifty.service.CategoriaService;
import cl.duoc.dej.gifty.service.ProductoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
@WebServlet(name = "ProductoListarServlet", urlPatterns = {"/listar"})
public class ProductoListarServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getSimpleName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoService productoService = new ProductoService();
        CategoriaService categoriaService = new CategoriaService();

        String productoBuscado = request.getParameter("producto");
        String stringCategoria = request.getParameter("categoria");
        Long categoriaBuscada = 0L;
        if (stringCategoria != null) {
            try {
                categoriaBuscada = Long.parseLong(stringCategoria);
            } catch(NumberFormatException nfe) {
                categoriaBuscada = 0L;
            }
        }

        List<Producto> listaProductos = null;
        if (productoBuscado == null && categoriaBuscada == 0) {
            logger.info("Mostrando todos los productos");
            listaProductos = productoService.getProductos();
        } else {
            logger.info(String.format("Buscando por nombre = %s y por categoria ID %s", productoBuscado, categoriaBuscada));
            listaProductos = productoService.buscarProductos(productoBuscado, categoriaBuscada);
        }

        List<Categoria> listaCategorias = categoriaService.getCategorias();

        request.setAttribute("productoBuscado", productoBuscado);
        request.setAttribute("categoriaBuscada", categoriaBuscada);
        request.setAttribute("productos", listaProductos);
        request.setAttribute("categorias", listaCategorias);
        request.getRequestDispatcher("/WEB-INF/jsp/producto/listar.jsp").forward(request, response);
    }

}

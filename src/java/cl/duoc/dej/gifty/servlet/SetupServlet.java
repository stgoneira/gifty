package cl.duoc.dej.gifty.servlet;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.entity.Producto;
import cl.duoc.dej.gifty.service.CategoriaService;
import cl.duoc.dej.gifty.service.ProductoService;
import cl.duoc.dej.gifty.service.SetupService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "SetupServlet", urlPatterns = {"/SetupServlet"})
public class SetupServlet extends HttpServlet {

    Logger logger;
    
    {
        logger = Logger.getLogger(this.getClass().getSimpleName());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parametros = request.getParameterMap();
        // revisa que existan parametros para procesar
        if( !parametros.isEmpty() && parametros.containsKey("operacion") ) {
            String operacion = parametros.get("operacion")[0];
            switch(operacion) {
                case "instalar":
                    instalar(request, response);
                    break;
                case "desinstalar":
                    desinstalar(request, response);
                    break;
                case "cargar":
                    cargarDatos(request, response);
                    break;
            }
        }
        request.getRequestDispatcher("/setup.jsp").forward(request, response);
    }

    private void instalar(HttpServletRequest request, HttpServletResponse response) {
        SetupService setupService = new SetupService();
        boolean resultado = setupService.instalar();
        String[] mensajes = resultado?new String[]{"Se instaló correctamento la aplicación"}:null;
        String[] errores = !resultado?new String[]{"Hubo problemas al instalar"}:null;
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
    }

    private void desinstalar(HttpServletRequest request, HttpServletResponse response) {
        SetupService setupService = new SetupService();
        boolean resultado = setupService.desinstalar();
        String[] mensajes = resultado?new String[]{"Se desinstaló correctamento la aplicación"}:null;
        String[] errores = !resultado?new String[]{"Hubo problemas al desinstalar"}:null;
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
    }

    private void cargarDatos(HttpServletRequest request, HttpServletResponse response) {
        List<Categoria> listaCategorias = new ArrayList<>();
        List<Producto> listaProductos = new ArrayList<>();
        
        Categoria categoriaNinos = new Categoria("Niños");
        listaCategorias.add(categoriaNinos);
        listaCategorias.add(new Categoria("Niñas"));
        listaCategorias.add(new Categoria("Juvenil Hombre"));
        listaCategorias.add(new Categoria("Juvenil Mujeres"));
        listaCategorias.add(new Categoria("Hombres"));
        listaCategorias.add(new Categoria("Mujeres"));
        listaCategorias.add(new Categoria("Otros"));
        
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = null;
        String mensaje = null;
        
        CategoriaService categoriaService = new CategoriaService();
        
        for(Categoria categoria: listaCategorias) {
            Categoria c = categoriaService.crearCategoria(categoria);
            if(c == null) {
                errores.add(String.format("No se pudo crear la categoría: %s", categoria.getNombre()));
            } else {
                categoria.setId(c.getId());
                mensajes.add(String.format("Se creó correctamente la categoría: %s", c.getNombre()));
            }
        }
        
        listaProductos.add(new Producto("Tren de madera", 15990L, "http://elpoderdelconsumidor.org/wp-content/uploads/2014/12/JuguetesTren-3.jpg", categoriaNinos));
        listaProductos.add(new Producto("Dinosaurio goma", 8590L, "https://d243u7pon29hni.cloudfront.net/images/products/juguete-electronico-silverlit-digidi-1308459-5_l.jpg", categoriaNinos));        
        listaProductos.add(new Producto("Camión juguete", 19500L, "https://www.tractordejuguete.com/upload_productos/1_img_1062bruder-2750-camion-remolcador.jpg", categoriaNinos));
        
        ProductoService productoService = new ProductoService();
        for(Producto producto:listaProductos) {
            Producto resultadoProducto = productoService.crearProducto(producto);
            if(resultadoProducto == null) {
                errores.add(String.format("No se pudo crear el producto: %s", producto.getNombre()));
            } else {
                mensajes.add(String.format("Se creó correctamente el producto: %s", producto.getNombre()));
            }
        }
        
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
    }
    
}

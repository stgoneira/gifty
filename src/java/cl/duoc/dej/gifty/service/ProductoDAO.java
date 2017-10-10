package cl.duoc.dej.gifty.service;

import cl.duoc.dej.gifty.entity.Producto;
import java.util.List;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public interface ProductoDAO {
    
    public Producto crearProducto(Producto producto);
    public boolean editarProducto(Producto producto);
    public boolean eliminarProducto(Producto producto);
    public List<Producto> buscarProductos(String nombre, String categoria);
    public List<Producto> getProductos();
}

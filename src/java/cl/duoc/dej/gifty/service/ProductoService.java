package cl.duoc.dej.gifty.service;

import cl.duoc.dej.gifty.entity.Categoria;
import cl.duoc.dej.gifty.entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class ProductoService implements ProductoDAO {

    Logger logger;

    {
        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    @Override
    public Producto crearProducto(Producto producto) {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "INSERT INTO productos(nombre, precio, imagen, categoria_id, fecha_creacion) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, producto.getNombre());
            prepareStatement.setLong(2, producto.getPrecio());
            prepareStatement.setString(3, producto.getImagen());
            prepareStatement.setLong(4, producto.getCategoria().getId());
            prepareStatement.setTimestamp(5, new Timestamp(producto.getFechaCreacion().getTimeInMillis()));
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            Long id = -1L;
            while (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            producto.setId(id);
            logger.log(Level.INFO, "Se guardó correctamente el producto: {0}", producto.getNombre());
            return producto;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Ocurri\u00f3 un error al guardar el producto: {0}", se.getMessage());
        }
        return null;
    }

    @Override
    public boolean editarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarProducto(Producto producto) {
        return eliminarProducto(producto.getId());
    }

    @Override
    public List<Producto> buscarProductos(String nombreBuscado, Long categoriaIdBuscada) {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = null;
            PreparedStatement prepareStatement = null;
            if (nombreBuscado != null && categoriaIdBuscada != null && nombreBuscado.length() > 0 && categoriaIdBuscada > 0) {
                sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ? AND categoria_id = ? ";
                prepareStatement = conexion.prepareStatement(sql);
                prepareStatement.setString(1, "%" + nombreBuscado.toLowerCase() + "%");
                prepareStatement.setLong(2, categoriaIdBuscada);
            } else if (nombreBuscado != null && nombreBuscado.length() > 0) {
                sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ? ";
                prepareStatement = conexion.prepareStatement(sql);
                prepareStatement.setString(1, "%" + nombreBuscado.toLowerCase() + "%");
            } else if (categoriaIdBuscada != null && categoriaIdBuscada > 0) {
                sql = "SELECT * FROM productos WHERE categoria_id = ? ";
                prepareStatement = conexion.prepareStatement(sql);
                prepareStatement.setLong(1, categoriaIdBuscada);
            } else {
                sql = "SELECT * FROM productos";
                prepareStatement = conexion.prepareStatement(sql);
            }

            ResultSet rs = prepareStatement.executeQuery();
            List<Producto> listaProductos = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Long precio = rs.getLong("precio");
                String imagen = rs.getString("imagen");
                Long categoriaId = rs.getLong("categoria_id");
                Timestamp timestampFechaCreacion = rs.getTimestamp("fecha_creacion");
                Calendar fechaCreacion = Calendar.getInstance();
                fechaCreacion.setTimeInMillis(timestampFechaCreacion.getTime());
                CategoriaService categoriaService = new CategoriaService();
                Categoria categoria = categoriaService.getCategoriaById(categoriaId);
                listaProductos.add(new Producto(id, nombre, precio, imagen, categoria, fechaCreacion));
            }
            return listaProductos;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Hubo un error al recuperar los Productos desde la BD: {0}", se.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> getProductos() {
        try {
            String sql = "SELECT * FROM productos";
            Connection conexion = Conexion.getConexion();
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            List<Producto> listaProductos = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Long precio = rs.getLong("precio");
                String imagen = rs.getString("imagen");
                Long categoriaId = rs.getLong("categoria_id");
                Timestamp timestampFechaCreacion = rs.getTimestamp("fecha_creacion");
                Calendar fechaCreacion = Calendar.getInstance();
                fechaCreacion.setTimeInMillis(timestampFechaCreacion.getTime());
                CategoriaService categoriaService = new CategoriaService();
                Categoria categoria = categoriaService.getCategoriaById(categoriaId);
                listaProductos.add(new Producto(id, nombre, precio, imagen, categoria, fechaCreacion));
            }
            return listaProductos;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Hubo un error al recuperar los Productos desde la BD: {0}", se.getMessage());
        }
        return null;
    }

    @Override
    public boolean eliminarProducto(Long productoId) {
        String error = null;
        String mensaje = null;
        try {
            String sql = "DELETE FROM productos WHERE id = ?";
            Connection conexion = Conexion.getConexion();
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.setLong(1, productoId);
            int resultado = prepareStatement.executeUpdate();
            if (resultado < 1) {
                throw new SQLException("No se encontró ningún registro que eliminar.");
            }
            mensaje = String.format("Se eliminó correctamente el producto con ID %s", productoId);
            logger.log(Level.INFO, mensaje);
            return true;
        } catch (SQLException se) {
            error = String.format("Ocurrió un error al eliminar el producto: %s", se.getMessage());
            logger.log(Level.SEVERE, error);
        }
        return false;
    }

}

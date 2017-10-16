package cl.duoc.dej.gifty.service;

import cl.duoc.dej.gifty.entity.Categoria;
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

public class CategoriaService implements CategoriaDAO {

    Logger logger;

    {
        logger = Logger.getLogger(getClass().getSimpleName());
    }

    public CategoriaService() {
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "INSERT INTO categorias(nombre, fecha_creacion) VALUES(?, ?)";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, categoria.getNombre());
            prepareStatement.setTimestamp(2, new Timestamp(categoria.getFechaCreacion().getTimeInMillis()));
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            Long id = -1L;
            while (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            categoria.setId(id);
            logger.log(Level.INFO, "Se guardó correctamente la categoría: {0}", categoria.getNombre());
            return categoria;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Ocurri\u00f3 un error al guardar la categor\u00eda: {0}", se.getMessage());
        }
        return null;
    }

    @Override
    public boolean editarCategoria(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarCategoria(Categoria categoria) {
        return eliminarCategoria(categoria.getId());
    }

    @Override
    public List<Categoria> getCategorias() {
        try {
            String sql = "SELECT * FROM categorias";
            Connection conexion = Conexion.getConexion();
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            List<Categoria> listaCategorias = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Timestamp timestampFechaCreacion = rs.getTimestamp("fecha_creacion");
                Calendar fechaCreacion = Calendar.getInstance();
                fechaCreacion.setTimeInMillis(timestampFechaCreacion.getTime());
                listaCategorias.add(new Categoria(id, nombre, fechaCreacion));
            }
            return listaCategorias;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Hubo un error al recuperar las Categorías desde la BD: {0}", se.getMessage());
        }
        return null;
    }

    @Override
    public boolean eliminarCategoria(Long categoriaId) {
        String error = null;
        String mensaje = null;
        try {
            String sql = "DELETE FROM categorias WHERE id = ?";
            Connection conexion = Conexion.getConexion();
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.setLong(1, categoriaId);
            int resultado = prepareStatement.executeUpdate();
            if(resultado < 1) {
                throw new SQLException("No se encontró ningún registro que eliminar.");
            }
            mensaje = String.format("Se eliminó correctamente la categoría con ID %s", categoriaId);
            logger.log(Level.INFO, mensaje);
            return true;
        } catch (SQLException se) {
            error = String.format("Ocurrió un error al eliminar la categoría: %s", se.getMessage());
            logger.log(Level.SEVERE, error);
        }
        return false;
    }

    @Override
    public Categoria getCategoriaById(Long categoriaId) {
        try {
            String sql = "SELECT * FROM categorias WHERE id = ?";
            Connection conexion = Conexion.getConexion();
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.setLong(1, categoriaId);
            ResultSet rs = prepareStatement.executeQuery();
            Categoria categoria = null;
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Timestamp timestampFechaCreacion = rs.getTimestamp("fecha_creacion");
                Calendar fechaCreacion = Calendar.getInstance();
                fechaCreacion.setTimeInMillis(timestampFechaCreacion.getTime());
                categoria = new Categoria(id, nombre, fechaCreacion);
            }
            return categoria;
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "Hubo un error al recuperar la Categoría con ID {0}: {1}", new Object[]{categoriaId, se.getMessage()});
        }
        return null;
    }

}

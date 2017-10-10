package cl.duoc.dej.gifty.service;

import com.sun.xml.ws.api.security.trust.Claims;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class SetupService {

    Connection conexion;
    Logger logger;

    public SetupService() {
        conexion = Conexion.getConexion();
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public boolean instalar() {
        try {
            conexion.setAutoCommit(false);
            String sqlCategorias = "CREATE TABLE categorias(\n"
                    + "	id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT\n"
                    + "	, nombre VARCHAR(255) NOT NULL\n"
                    + "	, fecha_creacion DATE DEFAULT NOT NULL\n"
                    + ");";
            String sqlProductos = "CREATE TABLE productos(\n"
                    + "	id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT\n"
                    + "	, nombre VARCHAR(255) NOT NULL\n"
                    + "	, precio BIGINT UNSIGNED NOT NULL\n"
                    + "	, imagen TEXT NOT NULL\n"
                    + "	, categoria_id BIGINT UNSIGNED NOT NULL\n"
                    + " , FOREIGN KEY (categoria_id) REFERENCES categorias(id)"
                    + ");";

            // Creación de tabla Categorias
            logger.log(Level.INFO, "Ejecutando SQL: {0}", sqlCategorias);
            PreparedStatement prepareStatementCategorias = conexion.prepareStatement(sqlCategorias);
            prepareStatementCategorias.execute();
            
            // Creación de tabla Productos
            logger.log(Level.INFO, "Ejecutando SQL: {0}", sqlProductos);
            PreparedStatement prepareStatementProductos = conexion.prepareStatement(sqlProductos);
            prepareStatementProductos.execute();
            
            // commit
            conexion.commit();

            // lo vuelvo al estado original
            conexion.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean desinstalar() {
        try {
            String sql = "DROP TABLE categorias; DROP TABLE productos;";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean cargarDatos() {
        throw new UnsupportedOperationException();
    }

    public boolean cargarDatosEjemplo() {
        throw new UnsupportedOperationException();
    }

}

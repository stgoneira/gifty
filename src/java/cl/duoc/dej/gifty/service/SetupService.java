package cl.duoc.dej.gifty.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                    + "	, nombre VARCHAR(255) UNIQUE NOT NULL\n"
                    + "	, fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP\n"
                    + ");";
            String sqlProductos = "CREATE TABLE productos(\n"
                    + "	id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT\n"
                    + "	, nombre VARCHAR(255) UNIQUE NOT NULL\n"
                    + "	, precio BIGINT UNSIGNED NOT NULL\n"
                    + "	, imagen TEXT NOT NULL\n"
                    + "	, categoria_id BIGINT UNSIGNED NOT NULL\n"
                    + "	, fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP\n"
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
            
            // vuelve al estado original
            conexion.setAutoCommit(true);
            
            logger.log(Level.INFO, "Se instaló correctamente la aplicación.");
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ocurri\u00f3 un error al intentar realizar la instalaci\u00f3n: {0}", e.getMessage());
            try {
                if(conexion != null) 
                    conexion.rollback();
            } catch(SQLException e2) {
                logger.log(Level.SEVERE, "Ocurri\u00f3 un error al realizar el Rollback{0}", e2.getMessage());
            }
        } 
        return false;
    }

    public boolean desinstalar() {
        try {
            conexion.setAutoCommit(false);
            String sqlProductos = "DROP TABLE productos";
            String sqlCategorias = "DROP TABLE categorias";
            PreparedStatement psProductos = conexion.prepareStatement(sqlProductos);
            PreparedStatement psCategorias = conexion.prepareStatement(sqlCategorias);
            psProductos.execute();
            psCategorias.execute();
            conexion.commit();
            
            // vuelve al estado original
            conexion.setAutoCommit(true);

            logger.log(Level.INFO, "Se desinstaló correctamente la aplicación.");
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ocurri\u00f3 un error al desinstalar:{0}", e.getMessage());
        }
        return false;
    }

}

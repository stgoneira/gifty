package cl.duoc.dej.gifty.entity;

import java.util.Calendar;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class Producto {

    private Long id;
    private String nombre;
    private Long precio;
    private String imagen;
    private Calendar fechaCreacion;
    private Categoria categoria;
    
    // bloque de inicialización de variables
    {
        fechaCreacion = Calendar.getInstance();
    }

    public Producto() {
    }

    public Producto(String nombre, Long precio, String imagen, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Producto(Long id, String nombre, Long precio, String imagen, Categoria categoria, Calendar fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
    }
    
    
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}

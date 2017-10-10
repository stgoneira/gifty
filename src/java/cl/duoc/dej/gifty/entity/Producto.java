package cl.duoc.dej.gifty.entity;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class Producto {

    private Long id;
    private String nombre;
    private Long precio;
    private String imagen;

    public Producto() {
    }

    public Producto(String nombre, Long precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(Long id, String nombre, Long precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
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
    
}

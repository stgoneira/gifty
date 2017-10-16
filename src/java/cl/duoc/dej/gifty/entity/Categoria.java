package cl.duoc.dej.gifty.entity;

import java.util.Calendar;

public class Categoria {

    private Long id;
    private String nombre;
    private Calendar fechaCreacion;
    
    // bloque de inicialización de variables
    {
        fechaCreacion = Calendar.getInstance();
    }

    // Constructores
    public Categoria(Long id, String nombre, Calendar fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    
    // getters y setters
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

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}

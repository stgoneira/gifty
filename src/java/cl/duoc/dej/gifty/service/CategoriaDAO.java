package cl.duoc.dej.gifty.service;

import cl.duoc.dej.gifty.entity.Categoria;
import java.util.List;

public interface CategoriaDAO {

    public Categoria crearCategoria(Categoria categoria);
    public boolean editarCategoria(Categoria categoria);
    public boolean eliminarCategoria(Categoria categoria);
    public boolean eliminarCategoria(Long categoriaId);
    public List<Categoria> getCategorias();
    public Categoria getCategoriaById(Long categoriaId);
    
}

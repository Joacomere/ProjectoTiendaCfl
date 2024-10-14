package net.cfl.tiendacosas.servicios.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.excepciones.CategoriaExistenteEx;
import net.cfl.tiendacosas.modelo.Categoria;
import net.cfl.tiendacosas.repositorio.CategoriaRepositorio;

@Service
@RequiredArgsConstructor

public class CategoriaServicio implements ICategoriaServicio {
	private final CategoriaRepositorio categoriaRepositorio;
	
	@Override
	public Categoria listaCategoriaPorId(Long id) {
		return categoriaRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoEx("Categoria no encontrada"));
	}

	@Override
	public Categoria listaCategoriaPorNombre(String nombre) {
		
		return categoriaRepositorio.findByNombre(nombre);
	}

	@Override
	public List<Categoria> listarCategorias() {
		
		return categoriaRepositorio.findAll();
	}

	@Override
	public Categoria agregaCategoria(Categoria categoria) {
		
		return Optional.of(categoria)
				.filter(c ->!categoriaRepositorio.existsByNombre(c.getNombre()))
				.map(categoriaRepositorio::save)
				.orElseThrow(()-> new CategoriaExistenteEx(categoria.getNombre() + "Ya existe en la base de datos"));
	}

	@Override
	public Categoria actualizaCategoria(Categoria categoriaNueva, Long id) {
		
		return Optional.ofNullable(listaCategoriaPorId(id)).map(categoriaVieja -> {
			categoriaVieja.setNombre(categoriaNueva.getNombre());
			return categoriaRepositorio.save(categoriaVieja);
		}).orElseThrow(()-> new RecursoNoEncontradoEx("Categoria no encontrada"));
	}

	@Override
	public void borrarCategoriaPorId(Long id) {
		categoriaRepositorio.findById(id).ifPresentOrElse(categoriaRepositorio::delete, ()->{
			new RecursoNoEncontradoEx("Categoria no encontrada");
		});
	}

}

package net.cfl.tiendacosas.servicios.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
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
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public List<Categoria> listarCategorias() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public Categoria agregaCategoria(Categoria categoria) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public Categoria actualizaCategoria(Categoria categoria) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public void borrarCategoriaPorId(Long id) {
		// TODO Apéndice de método generado automáticamente
		
	}

}

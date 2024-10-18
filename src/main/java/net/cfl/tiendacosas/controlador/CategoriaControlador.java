package net.cfl.tiendacosas.controlador;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.CategoriaExistenteEx;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Categoria;
import net.cfl.tiendacosas.respuesta.ApiRespuesta;
import net.cfl.tiendacosas.servicios.categoria.ICategoriaServicio;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categorias")
public class CategoriaControlador {
	public final ICategoriaServicio categoriaServicio;
	@GetMapping("/todas")
	public ResponseEntity<ApiRespuesta> listaTodasCategorias(){
		try {
			List<Categoria> categorias = categoriaServicio.listarCategorias();
			
			return ResponseEntity.ok(new ApiRespuesta("Encontrado!", categorias));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta("Error: ", HttpStatus.INTERNAL_SERVER_ERROR));
			
		}
	}
	@PostMapping("/agrega")
	public ResponseEntity<ApiRespuesta> agregaCategoria(@RequestBody Categoria categoria){
		try {
			Categoria laCategoria = categoriaServicio.agregaCategoria(categoria);
			
			return ResponseEntity.ok(new ApiRespuesta("Categoria agregada!", laCategoria));
		} catch (CategoriaExistenteEx e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/categoria/{categoriaId}/categoria")
	public ResponseEntity<ApiRespuesta> listaCategoriaPorId(@PathVariable Long categoriaId){
		try {
			Categoria laCategoria = categoriaServicio.listaCategoriaPorId(categoriaId);
			return ResponseEntity.ok(new ApiRespuesta("Encontrado!", laCategoria));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(), null));
		}
	}
	@GetMapping("/categoria/{categoriaNombre}/categoria")
	public ResponseEntity<ApiRespuesta> listaCategoriaPorNombre(@PathVariable Long categoriaNombre){
		try {
			Categoria laCategoria = categoriaServicio.listaCategoriaPorId(categoriaNombre);
			return ResponseEntity.ok(new ApiRespuesta("Encontrado!", laCategoria));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(), null));
		}
	}
	@DeleteMapping("/categoria/{categoriaId}/categoria")
	public ResponseEntity<ApiRespuesta> borraCategoria(Long categoriaId){
		try {
			categoriaServicio.borrarCategoriaPorId(categoriaId);
			return ResponseEntity.ok(new ApiRespuesta("Encontrado!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(), null));
		}
	}
	
	@PutMapping("/categoria/{categoriaId}/categoria")
	public ResponseEntity<ApiRespuesta> actualizaCategoria(@PathVariable Long categoriaId, @RequestBody Categoria categoria){
		try {
			Categoria categoriaActualizada = categoriaServicio.actualizaCategoria(categoria,categoriaId);
			return ResponseEntity.ok(new ApiRespuesta("Categoria actualizada!", categoriaActualizada));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(), null));
			}
		}
}


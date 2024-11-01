package net.cfl.tiendacosas.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.CarritoItem;
import net.cfl.tiendacosas.respuesta.ApiRespuesta;
import net.cfl.tiendacosas.servicios.carrito.ICarritoItemServicio;
import net.cfl.tiendacosas.servicios.carrito.ICarritoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/items-carrito")
public class CarritoItemControlador {
	private final ICarritoItemServicio carritoItemServicio;
	private final ICarritoServicio carritoServicio;
	@PostMapping("/item/agrega")
	public ResponseEntity<ApiRespuesta> agregaItemAlCarrito(@RequestParam(required = false) Long carritoId,@RequestParam Long productoId,@RequestParam Integer cantidad){
		try {
			if(carritoId == null) {
				carritoId = carritoServicio.inicializaCarrito();
			}
			carritoItemServicio.agregaItemAlCarrito(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item agregado con exito", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	@DeleteMapping("/carrito/{carritoId}/item/{productoId}/quitar")
	public ResponseEntity<ApiRespuesta> quitaItemDelCarrito(@PathVariable Long carritoId,@PathVariable Long productoId){
		try {
			carritoItemServicio.quitaItemDelCarrito(carritoId, productoId);
			return ResponseEntity.ok(new ApiRespuesta("Item quitado con exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	@PutMapping("/carrito/{carritoId}/item/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaCantidadDeItem(@PathVariable Long carritoId,@PathVariable Long productoId,@RequestParam Integer cantidad){
		try {
			carritoItemServicio.actualizaCantidadItems(carritoId,productoId,cantidad);
			return ResponseEntity.ok(new ApiRespuesta("Item actualizado con exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	@GetMapping("/carrito/{carritoId}/item/{productoId}/trae")
	public ResponseEntity<ApiRespuesta> traeCarritoItem(@PathVariable Long carritoId,@PathVariable Long productoId){
		try {
			CarritoItem carritoItem = carritoItemServicio.traeCarritoItem(carritoId, productoId);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", carritoItem));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
}

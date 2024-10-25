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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.dto.ProductoDto;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.request.ActualizaProductoRequest;
import net.cfl.tiendacosas.request.AgregaProductoRequest;
import net.cfl.tiendacosas.respuesta.ApiRespuesta;
import net.cfl.tiendacosas.servicios.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/productos")
public class ProductoControlador {
	private final IProductoServicio productoServicio;
	@GetMapping("/todos")
	public ResponseEntity<ApiRespuesta> listaTodosProductos(){
		List<Producto> productos = productoServicio.ListarProductos();
		List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
		return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
	}
	@GetMapping("/producto/{productoId}/producto")
	public ResponseEntity<ApiRespuesta> listarProductoPorId(@PathVariable Long productoId){
		try {
			Producto producto = productoServicio.listaProductoPorId(productoId);
			ProductoDto productoDto = productoServicio.convertirAProductoDto(producto);
			return ResponseEntity.ok(new ApiRespuesta("Exito!",productoDto));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@PostMapping("/agrega")
	public ResponseEntity<ApiRespuesta> agregarProducto(@RequestBody AgregaProductoRequest producto){
		try {
			Producto elProducto = productoServicio.agregaProducto(producto);
			ProductoDto productoDto = productoServicio.convertirAProductoDto(elProducto);
			return ResponseEntity.ok(new ApiRespuesta("Producto agregado!", productoDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@PutMapping("/producto/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaProducto(@RequestBody ActualizaProductoRequest request,@PathVariable Long productoId){
		try {
			Producto elProducto = productoServicio.actualizaProducto(request, productoId);
			ProductoDto productoDto = productoServicio.convertirAProductoDto(elProducto);
			return ResponseEntity.ok(new ApiRespuesta("Producto actualizado!", productoDto));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@DeleteMapping("/producto/{productoId}/borrar")
	public ResponseEntity<ApiRespuesta> borrarProducto(@PathVariable Long productoId){
		try {
			productoServicio.borrarProducto(productoId);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/por/marca-y-nombre")
	public ResponseEntity<ApiRespuesta> listarPorMarcaYNombre(@RequestBody String marcaNombre,@RequestBody String productoNombre){
		try {
			List<Producto> productos = productoServicio.ListarPorNombreYMarca(productoNombre, marcaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("No se encontraron productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/por/marca-y-categoria")
	public ResponseEntity<ApiRespuesta> listarPorMarcaYCategoria(@RequestBody String marcaNombre,@RequestBody String categoriaNombre){
		try {
			List<Producto> productos = productoServicio.ListarPorMarcaYCategoria(marcaNombre, categoriaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("No se encontraron productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/por/{productoNombre}/producto")
	public ResponseEntity<ApiRespuesta> listarPorNombre(@RequestBody String productoNombre){
		try {
			List<Producto> productos = productoServicio.ListarPorNombre(productoNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("No se encontraron productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/por/{marcaNombre}/producto")
	public ResponseEntity<ApiRespuesta> listarPorMarca(@RequestBody String marcaNombre){
		try {
			List<Producto> productos = productoServicio.ListarPorMarca(marcaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("No se encontraron productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/producto/{categoriaNombre}/todos/productos")
	public ResponseEntity<ApiRespuesta> listarPorCategoria(@RequestBody String categoriaNombre){
		try {
			List<Producto> productos = productoServicio.ListarPorCategoria(categoriaNombre);
			if(productos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("No se encontraron productos", null));
			}
			List<ProductoDto> productosConvertidos = productoServicio.traeProductosConvertidos(productos);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productosConvertidos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@GetMapping("/producto/cuenta/por/marca-y-nombre")
	public ResponseEntity<ApiRespuesta> contarPorNombreYMarca(@RequestBody String productoNombre, @RequestBody String marcaNombre){
		try {
			var productoCuenta = productoServicio.contarProductosPorNombreYMarca(productoNombre, marcaNombre);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", productoCuenta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
}

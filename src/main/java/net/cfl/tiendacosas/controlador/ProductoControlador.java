package net.cfl.tiendacosas.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.modelo.Producto;
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
		return ResponseEntity.ok(new ApiRespuesta("Exito!", productos));
	}
	
	public ResponseEntity<ApiRespuesta> listarProductoPorId(Long productoId){
		Producto producto = productoServicio.listaProductoPorId(productoId);
		return ResponseEntity.ok(new ApiRespuesta("Exito!",producto));
	}
}

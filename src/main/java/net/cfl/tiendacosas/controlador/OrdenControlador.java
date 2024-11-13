package net.cfl.tiendacosas.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Orden;
import net.cfl.tiendacosas.respuesta.ApiRespuesta;
import net.cfl.tiendacosas.servicios.orden.IOrdenServicio;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/ordenes")
public class OrdenControlador {
	private final IOrdenServicio ordenServicio;
	
	@PostMapping("/orden")
	public ResponseEntity<ApiRespuesta> creaOrden(@RequestParam Long usuarioId){
		try {
			Orden orden = ordenServicio.realizaOrden(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Orden creada!", orden));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@GetMapping("/{ordenId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenPorId(@PathVariable Long ordenId){
		try {
			Orden orden = ordenServicio.traeOrden(ordenId);
			return ResponseEntity.ok(new ApiRespuesta("Exito",orden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("Ooops!", e.getMessage()));
		}
	}
	
	@GetMapping("/{usuarioId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenUsuario(@PathVariable Long usuarioId){
		try {
			List<Orden> orden = ordenServicio.traeUsuarioOrdenes(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Exito",orden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("Ooops!", e.getMessage()));
		}
	}

}


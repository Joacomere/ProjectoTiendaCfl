package net.cfl.tiendacosas.controlador;

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
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.excepciones.UsuarioExistenteEx;
import net.cfl.tiendacosas.modelo.Usuario;
import net.cfl.tiendacosas.request.ActualizaUsuarioReq;
import net.cfl.tiendacosas.request.AgregaUsuarioReq;
import net.cfl.tiendacosas.respuesta.ApiRespuesta;
import net.cfl.tiendacosas.servicios.usuario.IUsuarioServicio;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/usuarios")
public class UsuarioControlador {
	private final IUsuarioServicio usuarioServicio;
	
	@GetMapping("/{usuarioId}/usuario")
	public ResponseEntity<ApiRespuesta> traeUsuario(@PathVariable Long usuarioId){
		try {
			Usuario usuario = usuarioServicio.traeUsuarioPorId(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Exito",usuario));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	@PostMapping("/agrega")
	public ResponseEntity<ApiRespuesta> creaUsuario(@RequestBody AgregaUsuarioReq request){
		try {
			Usuario usuario = usuarioServicio.crearUsuario(request);
			return ResponseEntity.ok(new ApiRespuesta("Exito",usuario));
		} catch (UsuarioExistenteEx e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@PutMapping("/{usuarioId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaUsuario(@RequestBody ActualizaUsuarioReq request,@PathVariable Long usuarioId){
		try {
			Usuario usuario = usuarioServicio.actualizarUsuario(request, usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Usuario actualizado", usuario));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
	@DeleteMapping("/{usuarioId}/borra")
	public ResponseEntity<ApiRespuesta> eliminaUsuario(@PathVariable Long usuarioId){
		try {
			usuarioServicio.borrarUsuario(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Usuario borrado", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(),null));
		}
	}
}

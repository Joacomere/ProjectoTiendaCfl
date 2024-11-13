package net.cfl.tiendacosas.servicios.usuario;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.excepciones.UsuarioExistenteEx;
import net.cfl.tiendacosas.modelo.Usuario;
import net.cfl.tiendacosas.repositorio.UsuarioRepositorio;
import net.cfl.tiendacosas.request.ActualizaUsuarioReq;
import net.cfl.tiendacosas.request.AgregaUsuarioReq;

@Service
@RequiredArgsConstructor
public class UsuarioServicio implements IUsuarioServicio {
	private final UsuarioRepositorio usuarioRepositorio;

	@Override
	public Usuario traeUsuarioPorId(Long usuarioId) {
		return usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new RecursoNoEncontradoEx("Usuario no encontrado!"));
	}

	@Override
	public Usuario crearUsuario(AgregaUsuarioReq request) {
		return Optional.of(request)
				.filter(usuario -> !usuarioRepositorio.existsByEmail(request.getEmail()))
				.map(req -> {
					Usuario usuario = new Usuario();
					usuario.setEmail(request.getEmail());
					usuario.setPwd(request.getPwd());
					usuario.setUsuarioNombre(request.getUsuarioNombre());
					usuario.setUsuarioApellido(request.getUsuarioApellido());
					return usuarioRepositorio.save(usuario);
				}).orElseThrow(() ->new UsuarioExistenteEx("El usuario" + request.getEmail() + "ya existe"));
	}

	@Override
	public Usuario actualizarUsuario(ActualizaUsuarioReq request, Long usuarioId) {
		return usuarioRepositorio.findById(usuarioId).map(usuarioExistente ->{
			usuarioExistente.setUsuarioNombre(request.getUsuarioNombre());
			usuarioExistente.setUsuarioApellido(request.getUsuarioApellido());
			return usuarioRepositorio.save(usuarioExistente);
		}).orElseThrow(() -> new RecursoNoEncontradoEx("Usuario no encontrado!"));
	}

	@Override
	public void borrarUsuario(Long usuarioId) {
		usuarioRepositorio.findById(usuarioId).ifPresentOrElse(usuarioRepositorio :: delete, () ->{
			throw new RecursoNoEncontradoEx("Usuario no encontrado!");
		});
		
	}

}

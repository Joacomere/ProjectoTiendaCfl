package net.cfl.tiendacosas.servicios.usuario;

import net.cfl.tiendacosas.modelo.Usuario;
import net.cfl.tiendacosas.request.ActualizaUsuarioReq;
import net.cfl.tiendacosas.request.AgregaUsuarioReq;

public interface IUsuarioServicio {
	Usuario traeUsuarioPorId(Long usuarioId);
	Usuario crearUsuario(AgregaUsuarioReq request);
	Usuario actualizarUsuario(ActualizaUsuarioReq request, Long usuarioId);
	void borrarUsuario(Long usuarioId);
}

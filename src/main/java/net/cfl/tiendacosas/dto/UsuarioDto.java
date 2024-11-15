package net.cfl.tiendacosas.dto;

import java.util.List;

import lombok.Data;
@Data
public class UsuarioDto {
	private Long id;
	private String usuarioNombre;
	private String usuarioApellido;
	private String email;
	private List<OrdenDto> ordenes;
	private CarritoDto carrito;
}

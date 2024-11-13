package net.cfl.tiendacosas.excepciones;

public class UsuarioExistenteEx extends RuntimeException {
	public UsuarioExistenteEx(String mensaje) {
		super(mensaje);
	}
}

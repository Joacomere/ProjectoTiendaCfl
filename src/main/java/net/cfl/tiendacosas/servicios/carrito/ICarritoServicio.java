package net.cfl.tiendacosas.servicios.carrito;

import java.math.BigDecimal;

import net.cfl.tiendacosas.modelo.Carrito;
import net.cfl.tiendacosas.modelo.Usuario;

public interface ICarritoServicio {
	Carrito traeCarrito(Long id);
	void limpiaCarrito(Long id);
	BigDecimal traePrecioTotal(Long id);
	Carrito inicializaCarrito(Usuario usuario);
	Carrito traeCarritoPorUsuarioId(Long usuarioId);
}

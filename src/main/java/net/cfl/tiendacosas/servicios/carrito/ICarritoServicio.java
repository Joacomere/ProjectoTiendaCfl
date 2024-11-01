package net.cfl.tiendacosas.servicios.carrito;

import java.math.BigDecimal;

import net.cfl.tiendacosas.modelo.Carrito;

public interface ICarritoServicio {
	Carrito traeCarrito(Long id);
	void limpiaCarrito(Long id);
	BigDecimal traePrecioTotal(Long id);
	Long inicializaCarrito();
}

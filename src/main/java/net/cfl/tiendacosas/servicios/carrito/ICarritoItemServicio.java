package net.cfl.tiendacosas.servicios.carrito;

import net.cfl.tiendacosas.modelo.CarritoItem;

public interface ICarritoItemServicio {
	void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad);
	void quitaItemDelCarrito(Long carritoId, Long productoId);
	void actualizaCantidadItems(Long carritoId, Long productoId, int cantidad);
	CarritoItem traeCarritoItem(Long carritoId, long productoId);
}

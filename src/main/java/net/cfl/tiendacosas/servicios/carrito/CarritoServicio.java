package net.cfl.tiendacosas.servicios.carrito;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Carrito;
import net.cfl.tiendacosas.repositorio.CarritoItemRepositorio;
import net.cfl.tiendacosas.repositorio.CarritoRepositorio;
@Service
@RequiredArgsConstructor
public class CarritoServicio implements ICarritoServicio{
	private final CarritoRepositorio carritoRepositorio;
	private final CarritoItemRepositorio carritoItemRepositorio;
	
	@Override
	public Carrito traeCarrito(Long id) {
		Carrito carrito = carritoRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoEx("Carrito no encontrado"));
		BigDecimal montoTotal = carrito.getCostoTotal();
		carrito.setCostoTotal(montoTotal);
		return carritoRepositorio.save(carrito);
	}

	@Override
	public void limpiaCarrito(Long id) {
		Carrito carrito = traeCarrito(id);
		carritoItemRepositorio.deleteAllByCarritoId(id);
		carrito.getCarritoItems().clear();
		carritoRepositorio.deleteById(id);
	}

	@Override
	public BigDecimal traePrecioTotal(Long id) {
		Carrito carrito = traeCarrito(id);
		return carrito.getCostoTotal();
	}

}

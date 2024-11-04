package net.cfl.tiendacosas.servicios.carrito;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	private final AtomicLong generadorId = new AtomicLong(0);
	
	@Override
	public Carrito traeCarrito(Long id) {
		Carrito carrito = carritoRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoEx("Carrito no encontrado"));
		BigDecimal montoTotal = carrito.getCostoTotal();
		carrito.setCostoTotal(montoTotal);
		return carritoRepositorio.save(carrito);
	}
	@Transactional //Permite la ejecucuion en bloque de las consultas sql
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
	/*Metodo utilizado creado para generar ids de carritos sin autenticacion
	 * del usuario, solo lo aplicamos para probar la api en esta etapa donde todavia no implementamos los usuarios
	 */
	@Override
	public Long inicializaCarrito() {
		Carrito nuevoCarrito = new Carrito();
		Long nuevoCarritoId = generadorId.incrementAndGet();
		nuevoCarrito.setId(nuevoCarritoId);
		return carritoRepositorio.save(nuevoCarrito).getId();
	}
}

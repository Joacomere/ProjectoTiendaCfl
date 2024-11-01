package net.cfl.tiendacosas.servicios.carrito;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Carrito;
import net.cfl.tiendacosas.modelo.CarritoItem;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.repositorio.CarritoItemRepositorio;
import net.cfl.tiendacosas.repositorio.CarritoRepositorio;
import net.cfl.tiendacosas.servicios.producto.IProductoServicio;
@RequiredArgsConstructor
@Service
public class CarritoItemServicio implements ICarritoItemServicio {

	private final CarritoItemRepositorio carritoItemRepositorio;
	private final CarritoRepositorio carritoRepositorio;
	private final IProductoServicio productoServicio;
	private final ICarritoServicio carritoServicio;
	
	@Override
	public void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad) {
		//1: Obtener carrito
		//2: Obtener el producto
		//3: Verificar si el producto existe en el carrito
		//4: Si existe, establecer la cantidad requerida 
		//5: Si NO existe, iniciar el ingreso del item
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		Producto producto = productoServicio.listaProductoPorId(productoId);
		CarritoItem carritoItem = carrito.getCarritoItems().stream().
				filter(item -> item.getProducto().getId()
						.equals(producto)).findFirst().orElse(new CarritoItem());
		if(carritoItem.getId() == null) {
			carritoItem.setCarrito(carrito);
			carritoItem.setProducto(producto);
			carritoItem.setCantidad(cantidad);
			carritoItem.setPrecioUni(producto.getPrecio());
		}else {
			carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
		}
		carritoItem.setPrecioTot();
		carrito.agregaItem(carritoItem);
		carritoItemRepositorio.save(carritoItem);
		carritoRepositorio.save(carrito);
	}

	@Override
	public void quitaItemDelCarrito(Long carritoId, Long productoId) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		CarritoItem itemARemover =  traeCarritoItem(carritoId, productoId); 
		carrito.quitaItem(itemARemover);
		carritoRepositorio.save(carrito);
	}

	@Override
	public void actualizaCantidadItems(Long carritoId, Long productoId, int cantidad) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		carrito.getCarritoItems().stream().filter(item -> item.getProducto().getId().equals(productoId)).findFirst()
		.ifPresent(item -> {
			item.setCantidad(cantidad);
			item.setPrecioUni(item.getProducto().getPrecio());
			item.setPrecioTot();
		});
		BigDecimal montoTotal = carrito.getCostoTotal();
		carrito.setCostoTotal(montoTotal);
		carritoRepositorio.save(carrito);	
	}
	@Override
	public CarritoItem traeCarritoItem(Long carritoId, Long productoId) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		return carrito.getCarritoItems().stream()
				.filter(item -> item.getProducto().getId().equals(productoId)).findFirst()
				.orElseThrow(() -> new RecursoNoEncontradoEx("Producto no encontrado!!"));
	}
	
}


package net.cfl.tiendacosas.servicios.orden;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.dto.OrdenDto;
import net.cfl.tiendacosas.enums.OrdenEstado;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Carrito;
import net.cfl.tiendacosas.modelo.Orden;
import net.cfl.tiendacosas.modelo.OrdenItem;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.repositorio.OrdenRepositorio;
import net.cfl.tiendacosas.repositorio.ProductoRepositorio;
import net.cfl.tiendacosas.servicios.carrito.CarritoServicio;
@Service
@RequiredArgsConstructor
public class OrdenServicio implements IOrdenServicio {
	private final OrdenRepositorio ordenRepositorio;
	private final ProductoRepositorio productoRepositorio;
	private final CarritoServicio carritoServicio;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Orden realizaOrden(Long usuarioId) {
		Carrito carrito = carritoServicio.traeCarritoPorUsuarioId(usuarioId);
		Orden orden = crearOrden(carrito);
		long ordenId = orden.getId();
		List<OrdenItem> listaDeItemsOrden = crearOrdenItem(ordenId, carrito);
		orden.setOrdenItems(new HashSet<>(listaDeItemsOrden));
		orden.setMontoTotal(calculaMontoTotal(listaDeItemsOrden));
		Orden ordenGuardada = ordenRepositorio.save(orden);
		carritoServicio.limpiaCarrito(carrito.getId());
		return ordenGuardada;
	}
	
	private List<OrdenItem> crearOrdenItem(Long ordenId, Carrito carrito){
		
		return carrito.getCarritoItems().stream().map(carritoItem -> {
			Producto producto = carritoItem.getProducto();
			producto.setStock(producto.getStock() - carritoItem.getCantidad());
			productoRepositorio.save(producto);
			return new OrdenItem(ordenId,producto,carritoItem.getCantidad(),carritoItem.getPrecioUni());
		}).toList();
	}
	
	private Orden crearOrden(Carrito carrito) {
		Orden orden = new Orden();
		//Establecer el usuario
		orden.setOrdenEstado(OrdenEstado.PENDIENTE);
		orden.setOrdenFecha(LocalDate.now());
		return orden;
	}
	
	private BigDecimal calculaMontoTotal(List<OrdenItem> listaDeItems) {
		return listaDeItems.stream().map(item -> item.getPrecio().multiply(new BigDecimal(item.getCantidad())))
				.reduce(BigDecimal.ZERO, BigDecimal :: add);
	}

	@Override
	public OrdenDto traeOrden(Long ordenId) {
		return ordenRepositorio.findById(ordenId).map(this :: convertirAOrdenDto)
		.orElseThrow(() -> new RecursoNoEncontradoEx("Orden no encontrada"));
	}
	@Override
	public List<OrdenDto> traeUsuarioOrdenes(Long usuarioId){
		List<Orden> ordenes = ordenRepositorio.findByUsuarioId(usuarioId);
		return ordenes.stream().map(this :: convertirAOrdenDto).toList();
	}
	private OrdenDto convertirAOrdenDto(Orden orden) {
		return modelMapper.map(orden, OrdenDto.class);
	}
}

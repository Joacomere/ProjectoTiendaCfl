package net.cfl.tiendacosas.servicios.producto;

import java.util.List;

import net.cfl.tiendacosas.dto.ProductoDto;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.request.ActualizaProductoRequest;
import net.cfl.tiendacosas.request.AgregaProductoRequest;

public interface IProductoServicio {
	Producto agregaProducto(AgregaProductoRequest request);
	Producto listaProductoPorId(Long id);
	
	void borrarProducto(Long id);
	Producto actualizaProducto(ActualizaProductoRequest request, Long id);
	
	
	List<Producto> ListarProductos();
	List<Producto> ListarPorCategoria(String categoria);
	List<Producto> ListarPorMarca(String marca);
	List<Producto> ListarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> ListarPorNombre(String nombre);
	List<Producto> ListarPorNombreYMarca(String nombre, String marca);
	
	Long contarProductosPorNombreYMarca(String nombre, String marca);
	ProductoDto convertirAProductoDto(Producto producto);
	List<ProductoDto> traeProductosConvertidos(List<Producto> productos);
}

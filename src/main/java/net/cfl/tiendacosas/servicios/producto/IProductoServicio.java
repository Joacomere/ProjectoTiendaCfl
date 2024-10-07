package net.cfl.tiendacosas.servicios.producto;

import java.util.List;

import net.cfl.tiendacosas.modelo.Producto;

public interface IProductoServicio {
	Producto agregaProducto(Producto producto);
	Producto listaProductoPorId(Long id);
	
	void borrarProducto(Long id);
	void actualizaProducto(Producto producto, Long id);
	
	List<Producto> ListarProductos();
	List<Producto> ListarPorCategoria(String categoria);
	List<Producto> ListarPorMarca(String marca);
	List<Producto> ListarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> ListarPorNombre(String nombre);
	List<Producto> ListarPorNombreYMarca(String nombre, String marca);
	
	Long contarProductosPorNombreYMarca(String nombre, String marca);
}

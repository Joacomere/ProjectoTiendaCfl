package net.cfl.tiendacosas.servicios.producto;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.excepciones.ProductoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.repositorio.ProductoRepositorio;

@Service
@RequiredArgsConstructor
public class ProductoServicio implements IProductoServicio{
	
	private ProductoRepositorio productoRepositorio;
	
	@Override
	public Producto agregaProducto(Producto producto) {
		
		return null;
	}

	@Override
	public Producto listaProductoPorId(Long id) {
		
		return productoRepositorio.findById(id)
				.orElseThrow(()-> new ProductoNoEncontradoEx("Producto no encontrado"));
	}

	@Override
	public void borrarProducto(Long id) {
		productoRepositorio.findById(id).ifPresentOrElse(productoRepositorio::delete,
				() -> new ProductoNoEncontradoEx("Producto no encontrado"));
		
	}

	@Override
	public void actualizaProducto(Producto producto, Long id) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public List<Producto> ListarProductos() {
		return productoRepositorio.findAll();
	}

	@Override
	public List<Producto> ListarPorCategoria(String categoria) {
		return productoRepositorio.findByAtCategoria(categoria);
	}

	@Override
	public List<Producto> ListarPorMarca(String marca) {
		return productoRepositorio.findByMarca(marca);
	}

	@Override
	public List<Producto> ListarPorMarcaYCategoria(String marca, String categoria) {
		return productoRepositorio.findByMarcaYAtCategoria(marca, categoria);
	}

	@Override
	public List<Producto> ListarPorNombre(String nombre) {
		return productoRepositorio.findByNombre(nombre);
	}

	@Override
	public List<Producto> ListarPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.findByNombreYMarca(nombre, marca);
	}

	@Override
	public Long contarProductosPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.countByNombreYMarca(nombre, marca);
	}

}

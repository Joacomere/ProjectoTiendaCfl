package net.cfl.tiendacosas.servicios.producto;

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.tiendacosas.dto.ImagenDto;
import net.cfl.tiendacosas.dto.ProductoDto;
import net.cfl.tiendacosas.excepciones.RecursoNoEncontradoEx;
import net.cfl.tiendacosas.modelo.Categoria;
import net.cfl.tiendacosas.modelo.Imagen;
import net.cfl.tiendacosas.modelo.Producto;
import net.cfl.tiendacosas.repositorio.CategoriaRepositorio;
import net.cfl.tiendacosas.repositorio.ImagenRepositorio;
import net.cfl.tiendacosas.repositorio.ProductoRepositorio;
import net.cfl.tiendacosas.request.ActualizaProductoRequest;
import net.cfl.tiendacosas.request.AgregaProductoRequest;

@Service
@RequiredArgsConstructor
public class ProductoServicio implements IProductoServicio{
	
	private final ProductoRepositorio productoRepositorio;
	private final CategoriaRepositorio categoriaRepositorio;
	private final ImagenRepositorio imagenRepositorio;
	private final ModelMapper modelMapper;
	
	@Override
	public Producto agregaProducto(AgregaProductoRequest request) {
		
		 // Comprobar que exista la categoria en la base de datos. Si existe, la establecemos
		 // como categoria del producto. 
		 // Si no existe , la guardamos en la base de datos y la establecemos como categoria del producto 
		 Categoria categoria = Optional.ofNullable(categoriaRepositorio.findByNombre(request.getCategoria().getNombre()))
		 	.orElseGet(() -> {
		 		Categoria nuevaCategoria = new Categoria(request.getCategoria().getNombre());
		 		return categoriaRepositorio.save(nuevaCategoria);
		 	});
		 request.setCategoria(categoria);
		 return productoRepositorio.save(creaProducto(request, categoria));
		 
		 
	}

	private Producto creaProducto(AgregaProductoRequest request, Categoria categoria ) {
		return new Producto(
				request.getNombre(),
				request.getMarca(),
				request.getDescripcion(),
				request.getPrecio(),
				request.getStock(),
				categoria
				);
	}
	
	@Override
	public Producto listaProductoPorId(Long id) {
		
		return productoRepositorio.findById(id)
				.orElseThrow(()-> new RecursoNoEncontradoEx("Producto no encontrado"));
	}

	@Override
	public void borrarProducto(Long id) {
		productoRepositorio.findById(id).ifPresentOrElse(productoRepositorio::delete,
				() -> new RecursoNoEncontradoEx("Producto no encontrado"));
		
	}

	@Override
	public Producto actualizaProducto(ActualizaProductoRequest request, Long productoId) {
		return productoRepositorio.findById(productoId).map(productoExistente -> actualizaProductoExistente(productoExistente, request))
				.map(productoRepositorio::save)
				.orElseThrow(() -> new RecursoNoEncontradoEx("prodcuto no encontrado"));
		
	}
	
	public Producto actualizaProductoExistente(Producto productoExistente, ActualizaProductoRequest request) {
		productoExistente.setNombre(request.getNombre());
		productoExistente.setMarca(request.getMarca());
		productoExistente.setPrecio(request.getPrecio());
		productoExistente.setDescripcion(request.getDescripcion());
		productoExistente.setStock(request.getStock());
		Categoria categoria = categoriaRepositorio.findByNombre(request.getCategoria().getNombre());
		productoExistente.setCategoria(categoria);
		
		return productoExistente;
	}

	@Override
	public List<Producto> ListarProductos() {
		return productoRepositorio.findAll();
	}

	@Override
	public List<Producto> ListarPorCategoria(String categoria) {
		return productoRepositorio.findByCategoriaNombre(categoria);
	}

	@Override
	public List<Producto> ListarPorMarca(String marca) {
		return productoRepositorio.findByMarca(marca);
	}

	@Override
	public List<Producto> ListarPorMarcaYCategoria(String marca, String categoria) {
		return productoRepositorio.findByMarcaAndCategoriaNombre(marca, categoria);
	}

	@Override
	public List<Producto> ListarPorNombre(String nombre) {
		return productoRepositorio.findByNombre(nombre);
	}

	@Override
	public List<Producto> ListarPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.findByNombreAndMarca(nombre, marca);
	}

	@Override
	public Long contarProductosPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.countByNombreAndMarca(nombre, marca);
	}
	@Override
	public List<ProductoDto> traeProductosConvertidos(List<Producto> productos){
		return productos.stream().map(this :: convertirAProductoDto).toList();
		}
	@Override
	public ProductoDto convertirAProductoDto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
		List<Imagen> imagenes = imagenRepositorio.findByProductoId(producto.getId());
		List<ImagenDto> imagenesDto = imagenes.stream().map(imagen -> modelMapper.map(imagen, ImagenDto.class)).toList();
		productoDto.setImagenes(imagenesDto);
		return productoDto;
	}
}

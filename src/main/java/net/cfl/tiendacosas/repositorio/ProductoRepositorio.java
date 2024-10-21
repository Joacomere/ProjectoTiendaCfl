package net.cfl.tiendacosas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	List<Producto> findByCategoriaNombre(String categoriaNombre);
	List<Producto> findByMarca(String marca);
	List<Producto> findByMarcaAndCategoriaNombre(String marca, String categoriaNombre);
	List<Producto> findByNombre(String nombre);
	List<Producto> findByNombreAndMarca(String nombre, String marca);
	Long countByNombreAndMarca(String nombre, String marca);
}

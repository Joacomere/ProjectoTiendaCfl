package net.cfl.tiendacosas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	List<Producto> findByAtCategoria(String categoria);
	List<Producto> findByMarca(String marca);
	List<Producto> findByMarcaYAtCategoria(String marca, String categoria);
	List<Producto> findByNombre(String nombre);
	List<Producto> findByNombreYMarca(String nombre, String marca);
	Long countByNombreYMarca(String nombre, String marca);
}

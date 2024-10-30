package net.cfl.tiendacosas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {

}

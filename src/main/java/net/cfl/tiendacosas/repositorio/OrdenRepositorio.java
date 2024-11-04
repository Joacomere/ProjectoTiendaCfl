package net.cfl.tiendacosas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Orden;

public interface OrdenRepositorio extends JpaRepository<Orden, Long>{

}

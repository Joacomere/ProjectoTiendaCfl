package net.cfl.tiendacosas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Imagen;

public interface ImagenRepositorio extends JpaRepository<Imagen, Long>  {

}

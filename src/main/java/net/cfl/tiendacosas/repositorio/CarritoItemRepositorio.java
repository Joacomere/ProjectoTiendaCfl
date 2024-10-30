package net.cfl.tiendacosas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.CarritoItem;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem,Long> {
	void deleteAllByCarritoId(Long id);
}

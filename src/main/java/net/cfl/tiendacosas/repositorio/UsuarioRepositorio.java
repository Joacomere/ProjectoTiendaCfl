package net.cfl.tiendacosas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.tiendacosas.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	Boolean existsByEmail(String email);
}

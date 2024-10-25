package net.cfl.tiendacosas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import net.cfl.tiendacosas.modelo.Categoria;
import net.cfl.tiendacosas.modelo.Imagen;

@Data
public class ProductoDto {
	private Long id;
	private String nombre;
	private String marca;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Categoria categoria;
	private List<ImagenDto> imagenes;
}

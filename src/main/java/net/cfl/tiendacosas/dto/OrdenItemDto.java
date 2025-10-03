package net.cfl.tiendacosas.dto;

import java.math.BigDecimal;

import lombok.Data;
import net.cfl.tiendacosas.modelo.Orden;
import net.cfl.tiendacosas.modelo.Producto;
@Data
public class OrdenItemDto {
	private Long ordenId;
	
	private Long id;
	private Producto producto;
	private int cantidad;
	private BigDecimal precio;
}

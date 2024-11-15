package net.cfl.tiendacosas.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class CarritoItemDto {
	private Long id;
	private Integer cantidad;
	private BigDecimal precioUni;
	private BigDecimal precioTot;
	private ProductoDto producto;
}

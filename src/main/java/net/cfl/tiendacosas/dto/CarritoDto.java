package net.cfl.tiendacosas.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;
@Data
public class CarritoDto {
	private Long id;
	private BigDecimal costoTotal;
	private Set<CarritoItemDto> carritoItems;
	
}

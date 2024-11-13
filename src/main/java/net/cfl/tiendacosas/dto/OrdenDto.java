package net.cfl.tiendacosas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import net.cfl.tiendacosas.enums.OrdenEstado;

@Data
public class OrdenDto {
	private Long id;
	private Long usuarioId;
	private LocalDate ordenFecha;
	private BigDecimal montoTotal;
	private OrdenEstado ordenEstado;
	private List<OrdenItemDto> items;
	
}

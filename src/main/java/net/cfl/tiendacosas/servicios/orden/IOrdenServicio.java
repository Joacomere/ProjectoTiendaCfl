package net.cfl.tiendacosas.servicios.orden;

import java.util.List;

import net.cfl.tiendacosas.dto.OrdenDto;
import net.cfl.tiendacosas.modelo.Orden;

public interface IOrdenServicio {
	Orden realizaOrden(Long usuarioId);
	OrdenDto traeOrden(Long ordenId);
	List<OrdenDto> traeUsuarioOrdenes(Long usuarioId);
}

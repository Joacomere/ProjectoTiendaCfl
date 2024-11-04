package net.cfl.tiendacosas.servicios.orden;

import net.cfl.tiendacosas.modelo.Orden;

public interface IOrdenServicio {
	Orden ralizaOrden(Long usuarioId);
	Orden traeOrden(Long ordenId);
}

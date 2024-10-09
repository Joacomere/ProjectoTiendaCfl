package net.cfl.tiendacosas.request;

import java.math.BigDecimal;

import lombok.Data;
import net.cfl.tiendacosas.modelo.Categoria;
@Data
public class ActualizaProductoRequest {		 
		private Long id;
		private String nombre;
		private String marca;
		private String descripcion;
		private BigDecimal precio;
		private int stock;
		private Categoria categoria;
}

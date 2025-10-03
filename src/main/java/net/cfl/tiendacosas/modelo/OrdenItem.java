package net.cfl.tiendacosas.modelo;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrdenItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Long ordenId;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	private int cantidad;
	private BigDecimal precio;
	
	public OrdenItem(Long ordenId, Producto producto, int cantidad, BigDecimal precio) {
		this.ordenId = ordenId;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
}

package net.cfl.tiendacosas.modelo;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.cfl.tiendacosas.enums.OrdenEstado;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate ordenFecha;
	private BigDecimal montoTotal;
	@Enumerated(EnumType.STRING)
	private OrdenEstado ordenEstado;
	@OneToMany(mappedBy = "orden",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrdenItem> ordenItems = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}

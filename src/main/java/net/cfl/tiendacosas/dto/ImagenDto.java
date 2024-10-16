package net.cfl.tiendacosas.dto;

import java.sql.Blob;

import lombok.Data;

@Data
public class ImagenDto {
	private Long imagenId;
	private String imagenNombre;
	private String descargaUrl;
}

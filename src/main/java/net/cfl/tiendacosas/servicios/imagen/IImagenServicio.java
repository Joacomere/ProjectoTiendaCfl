package net.cfl.tiendacosas.servicios.imagen;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.tiendacosas.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	Imagen guardaImagen(MultipartFile archivo, Long productoId);
	void actualizaImagen(MultipartFile archivo,Long imagenId);
}

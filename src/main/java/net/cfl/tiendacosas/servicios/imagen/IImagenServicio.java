package net.cfl.tiendacosas.servicios.imagen;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.tiendacosas.dto.ImagenDto;
import net.cfl.tiendacosas.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	List<ImagenDto> guardaImagenes(List<MultipartFile> archivo, Long productoId);
	void actualizaImagen(MultipartFile archivo,Long imagenId);
}

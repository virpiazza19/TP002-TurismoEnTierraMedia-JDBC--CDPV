package dao;

import java.util.List;

import tierraMedia.Itinerario;
import tierraMedia.Producto;

public interface ItinerarioDAO {
	
	public abstract Itinerario findByNombreUsuario(String nombre);
	public abstract List<Producto> findAll(int id, List<Producto> p);

}
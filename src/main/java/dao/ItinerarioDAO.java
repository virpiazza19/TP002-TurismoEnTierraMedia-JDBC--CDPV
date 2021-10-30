package dao;

import tierraMedia.Itinerario;

public interface ItinerarioDAO {
	
	public abstract Itinerario findByNombreUsuario(String nombre);

}

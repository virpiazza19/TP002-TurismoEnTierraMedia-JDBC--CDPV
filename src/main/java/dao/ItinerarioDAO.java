package dao;

import tierraMedia.Itinerario;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{
	
	public abstract Itinerario findByNombreUsuario(String nombre);

}

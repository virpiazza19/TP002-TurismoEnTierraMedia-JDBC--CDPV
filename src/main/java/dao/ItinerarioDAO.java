package dao;

import java.util.List;
import tierraMedia.Itinerario;


public interface ItinerarioDAO {
	
	public abstract Itinerario findByNombreUsuario(String nombre);
	
	public abstract List<Itinerario> findAll();
}

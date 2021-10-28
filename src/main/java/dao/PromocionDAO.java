package dao;

import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{
	
	public List<Promocion> findAllPromo(List<Atraccion> lista);
	
}
package dao;

import java.util.List;

import tierraMedia.Atraccion;
import tierraMedia.Promocion;

public interface PromocionDAO {
	
	public List<Promocion> findAllPromo(List<Atraccion> lista);
	
}
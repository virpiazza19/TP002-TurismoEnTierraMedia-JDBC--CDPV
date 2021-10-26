package dao;

import tierraMedia.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	
	public Atraccion findByAtraccion(String nombre);

}
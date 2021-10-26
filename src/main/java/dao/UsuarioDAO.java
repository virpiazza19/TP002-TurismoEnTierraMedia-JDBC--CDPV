package dao;

import tierraMedia.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario findByUsername(String nombre);
}
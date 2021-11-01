package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import daos.AtraccionDAO;
import daos.DAOFactory;
import daos.ItinerarioDAO;
import daos.PromocionDAO;
import daos.UsuarioDAO;
import excepciones.NoHayCupoException;
import tierraMedia.Atraccion;
import tierraMedia.Itinerario;
import tierraMedia.Producto;
import tierraMedia.Promocion;
import tierraMedia.Usuario;


public class App {

	public static void main(String[] args) throws NoHayCupoException {
		
		ParqueTierra parque = new ParqueTierra();
		System.out.println(parque.generarUsuarios());
		System.out.println(parque.generarProductos());
		parque.ofrecerServicios (parque.usuarios, parque.productos);
		
	}
}
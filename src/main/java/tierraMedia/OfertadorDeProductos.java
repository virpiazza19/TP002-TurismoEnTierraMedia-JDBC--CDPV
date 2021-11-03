package tierraMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import daos.AtraccionDAO;
import daos.DAOFactory;
import daos.ItinerarioDAO;
import daos.UsuarioDAO;
import excepciones.NoHayCupoException;

public class OfertadorDeProductos {

	public void sugerirProductos(List<Usuario> usuarios, List<Producto> productos) throws NoHayCupoException {

		for (Usuario usuario : usuarios) {

			ItinerarioDAO itinerarioGeneral = DAOFactory.getItinerarioDAO();

			// Recuperamos el itinerario de un usuario en la BD
			usuario.setItinerario(itinerarioGeneral.findAll(usuario.getId(), productos));

			System.out.println(usuario.getProductosEnItinerario());

			productos.sort(new ComparadorPorTipoAtraccion(usuario.getAtraccionPreferida()));
			// ACA DEBERÃ�A CHEQUEAR SI EL USUARIO YA ESTA DENTRO DE ITINERARIO, SI ESTA EL
			// MENSAJE DEBERÃ�A SER: Â¡Bienvenido nuevamente!
			System.out.println(
					"\n--------------------------------------------------------------------------------------------------\n");
			System.out.println("\t\t\t\t Â¡BIENVENIDO NUEVO USUARIO!");

			for (Producto producto : productos) {
				List<Producto> itinerario = usuario.getProductosEnItinerario();

				boolean contiene = false;
				Iterator<Producto> iterador = itinerario.iterator();
				while (!contiene && iterador.hasNext()) {
					contiene = iterador.next().contiene(producto);
				}

				boolean hayCupo = producto.hayCupo();

				boolean puedeComprar = puedeComprar(usuario, producto);

				if (!contiene && hayCupo && puedeComprar) {
					System.out.println(
							"\n--------------------------------------------------------------------------------------------------\n");
					System.out.println("Usuario: " + usuario.getNombre() + "  Presupuesto: " + usuario.getPresupuesto()
							+ "  Tiempo Disponible: " + usuario.getTiempoDisponible()
							+ "  Tipo de AtracciÃ³n Favorito: " + usuario.getAtraccionPreferida());
					System.out.println(
							"\n--------------------------------------------------------------------------------------------------\n");
					System.out.println(producto);
					if (this.decisionUsuario().toUpperCase().equals("SI")) {
						usuario.agregarProductosAlItinerario(producto);
						usuario.agregarProductoNuevo(producto);
						producto.disminuirCupo();
					}
				}
			}
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			// AtraccionDAO.update(atraccion);
			usuarioDAO.update(usuario);
			System.out.println(
					"\n--------------------------------------------------------------------------------------------------\n");
			System.out.println("\t\t\t\t RESUMEN DE ITINERARIO\n");
			System.out.println(usuario.itinerario);
			System.out.println("\t\t\t\t COSTO TOTAL: " + usuario.itinerario.costoTotal() + " monedas.");
			System.out.println("\t\t\t\t DURACION TOTAL: " + usuario.itinerario.duracionTotal() + " horas.");

			for (Producto producto : usuario.getNuevosProductos()) {
				itinerarioGeneral.insert(usuario, producto);
			}
		}
		System.out.println("\n\n\n\t\t\t\t FIN PROGRAMA");
	}

	@SuppressWarnings("resource")
	private String decisionUsuario() {
		Scanner sc = new Scanner(System.in);
		String opcion = "";
		System.out.println("\n\nÂ¿Desea aÃ±adir la sugerencia a su ITINERARIO?");
		System.out.print("\nIngrese SI o No: ");
		opcion = sc.next();
		System.out.println();
		while (!opcion.toUpperCase().equals("SI") && !opcion.toUpperCase().equals("NO")) {
			System.out.println("Ingrese SI o No: ");
			opcion = sc.next();
			System.out.println();
		}
		return opcion;
	}

	private boolean puedeComprar(Usuario u, Producto p) {
		if (u.getPresupuesto() < p.getCosto() || u.getTiempoDisponible() < p.getDuracion()) {
			return false;
		}
		return true;
	}
}

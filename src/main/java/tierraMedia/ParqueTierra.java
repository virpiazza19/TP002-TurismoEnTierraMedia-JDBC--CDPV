package tierraMedia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParqueTierra {

	List<Usuario> usuarios = new LinkedList<Usuario>();
	List<Producto> productos = new ArrayList<Producto>();
	OfertadorDeProductos ofertador = new OfertadorDeProductos();

	public ParqueTierra (String archivoUsuarios, String archivoAtracciones, String archivoPromociones) {
		this.usuarios = generarUsuarios(archivoUsuarios);
		this.productos = generarProductos(archivoAtracciones, archivoPromociones);
	}
	
	public void ofrecerServicios (List<Usuario> usuarios, List<Producto> productos) throws NoHayCupoException  {
		ofertador.sugerirProductos(this.usuarios, this.productos);
	}

	private List<Usuario> generarUsuarios(String archivo) {
		LectorUsuario lu = new LectorUsuario();
		return lu.leerUsuarios(archivo);
	}

	private List<Producto> generarProductos(String atracciones, String promociones) {
		LectorAtracciones la = new LectorAtracciones();
		LectorPromociones lp = new LectorPromociones();
		List<Producto> productos = new ArrayList<Producto>();
		List<Atraccion> a = la.leerAtracciones(atracciones);
		List<Promocion> p = lp.leerPromociones(a, promociones);

		for (Promocion promocion : p) {
			productos.add(promocion);
		}

		for (Atraccion atraccion : a) {
			productos.add(atraccion);
		}

		return productos;
	}
}
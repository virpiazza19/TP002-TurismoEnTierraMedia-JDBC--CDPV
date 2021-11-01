package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	protected List<Producto> productos = new ArrayList<Producto>();
	private Usuario usuario;
	
	public Itinerario() {
	}

	public Itinerario(Usuario usuario, List<Producto> productos) {
		this.usuario = usuario;
		this.productos = productos;
	}

	public Itinerario(int id) {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	protected void agregarProductos(Producto p) {
		this.productos.add(p);
	}

	protected double duracionTotal() {
		double horas = 0.0;

		for (Producto producto : productos) {
			horas += producto.getDuracion();
		}

		return horas;
	}

	protected int costoTotal() {
		int monedas = 0;

		for (Producto producto : productos) {
			monedas += producto.getCosto();
		}

		return monedas;
	}

	@Override
	public String toString() {
		String cadena = "";

		for (Producto producto : productos) {
			cadena += producto + "\n";
		}

		return cadena;
	}
}

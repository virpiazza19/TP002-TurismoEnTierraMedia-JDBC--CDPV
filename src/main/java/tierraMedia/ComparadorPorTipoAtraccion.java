package tierraMedia;

import java.util.Comparator;

import enums.TipoAtraccion;

public class ComparadorPorTipoAtraccion implements Comparator<Producto> { 
	private TipoAtraccion tipoPreferido;

	public ComparadorPorTipoAtraccion(TipoAtraccion tipoPreferido) {
		this.tipoPreferido = tipoPreferido;
	}

	public int compare(Producto P1, Producto P2) {
		if (P1.tipoAtraccion == this.tipoPreferido && P2.tipoAtraccion == this.tipoPreferido) {
			if (P1.esPromo() && P2.esPromo()) {
				return comparaPorCostoyPorTiempo(P1, P2);
			} else {
				return -Boolean.compare(P1.esPromo(), P2.esPromo());
			}
		} else if (P1.tipoAtraccion != this.tipoPreferido && P2.tipoAtraccion != this.tipoPreferido) {
			if (P1.esPromo() && P2.esPromo()) {
				return comparaPorCostoyPorTiempo(P1, P2);
			} else if (!P1.esPromo() && !P2.esPromo()) {
				return comparaPorCostoyPorTiempo(P1, P2);
			} else {
				return -Boolean.compare(P1.esPromo(), P2.esPromo());
			}
		} else {
			if (P1.tipoAtraccion == this.tipoPreferido)
				return -1;
			return 1;
		}
	}

	private int comparaPorCostoyPorTiempo(Producto P1, Producto P2) {
		if (Integer.compare(P1.costo, P2.costo) == 0) {
			return -Double.compare(P1.duracion, P2.duracion);
		} else {
			return -Double.compare(P1.costo, P2.costo);
		}
	}

}

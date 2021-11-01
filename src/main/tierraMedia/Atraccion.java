package tierraMedia;

public class Atraccion extends Producto {

	private int cupo;
	
	public Atraccion(String nombre, int costo, double duracion, int cupo,TipoAtraccion tipo) {
		super.nombre = nombre;
		super.costo = costo;
		super.duracion = duracion;
		super.tipoAtraccion = tipo;
		this.cupo = cupo;
	}

	public int getCupo() {
		return cupo;
	}
	
	@Override
	protected boolean hayCupo() {
		return this.getCupo() > 0;
	}
	

	protected void disminuirCupo() throws NoHayCupoException {
		if (!hayCupo()) {
			throw new NoHayCupoException("Se agoto el CUPO en la atraccion: " + this.getNombre());
		}
		this.cupo--;
	}


	@Override
	public String toString() {
		return "ATRACCION \n Nombre: " + super.getNombre() + " | Costo: " + super.getCosto() +" Monedas | Duración: " + super.getDuracion() + " horas | Tipo: " + super.getTipoAtraccion(); 
	}
	

	@Override
	public boolean contiene(Producto p) {
		if (p.esPromo()) {
			return p.contiene(this);
		}
		
		return this.equals(p);
	}
}

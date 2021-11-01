package tierraMedia;

public class App {
	public static void main(String[] args) throws NoHayCupoException {
		ParqueTierra parque = new ParqueTierra ("archivosDeEntrada/usuarios.csv", 
				"archivosDeEntrada/atracciones.csv","archivosDeEntrada/promociones.csv");
		parque.ofrecerServicios (parque.usuarios, parque.productos);
	}
}

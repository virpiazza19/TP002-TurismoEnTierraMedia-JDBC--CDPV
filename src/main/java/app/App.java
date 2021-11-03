package app;

import excepciones.NoHayCupoException;

public class App {

	public static void main(String[] args) throws NoHayCupoException {
		
		ParqueTierra parque = new ParqueTierra();
//		System.out.println(parque.generarUsuarios());
//		System.out.println(parque.generarProductos());
		parque.generarUsuarios();
		parque.generarProductos();
		parque.ofrecerServicios (parque.usuarios, parque.productos);
	}
}
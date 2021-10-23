package tierraMedia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorDeArchivosDeUsuario {

	public static void crearArchivoDeSalida(Usuario U, String archivoSalida) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));

		bw.write("\n=====================================================\n");
		bw.write("RESUMEN DE TU CUENTA \n");
		bw.write("Usuario: " + U.getNombre() + "\nPresupuesto actual: " + U.getPresupuesto() + 
				" monedas de oro \nTiempo disponible actual: "
				+ U.getTiempoDisponible() + " horas");
		bw.write("\n---------------------------------------------------------\n");
		bw.write("El itinerario comprado incluye: \n" + (U.itinerario));
		bw.newLine();
		bw.write("COSTO TOTAL:  " + U.itinerario.costoTotal() + " monedas de oro\n");
		bw.write("DURACION TOTAL: " + U.itinerario.duracionTotal() + " horas\n");
		bw.write("\n---- GRACIAS " + U.getNombre().toUpperCase() + " POR TU VISITA ----\n");
		bw.write("\n=====================================================\n");
		bw.close();
	}
}

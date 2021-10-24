package tierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorAtracciones {
	private FileReader fr = null;
	private BufferedReader br = null;
	
	public List<Atraccion> leerAtracciones(String archivo) {
		List<Atraccion> atracciones= new ArrayList<Atraccion>();
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				try {
				atracciones.add(crearAtraccion(linea));
				} catch (AtraccionException e) { 
					System.out.println(e.getMessage());
				}
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return atracciones;	
	}
	
	private Atraccion crearAtraccion(String linea) throws AtraccionException {
		String[] lin = linea.split(",");
		if (lin.length != 5) { 
			throw new AtraccionException("Cantidad incorrecta de parametros");
		}
		Atraccion A;
		try {
		A = new Atraccion(lin[0], Integer.parseInt(lin[1]), Double.parseDouble(lin[2]), 
				Integer.parseInt(lin[3]), TipoAtraccion.valueOf(lin[4].toUpperCase()));
		} catch (NumberFormatException e)  {
			throw new AtraccionException("Uno de los parametros ingresados en la linea [" + linea +  "] no es un numero valido");
		} catch (IllegalArgumentException e) {
			throw new AtraccionException("El parametro " + lin[4] + " ingresado en la linea [" + linea +  "] no corresponde a un tipo de atraccion valido");
	}
		return A;
		}	
}

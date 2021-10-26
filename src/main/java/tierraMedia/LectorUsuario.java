package tierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorUsuario {
	private FileReader fr = null;
	private BufferedReader br = null;

	public List<Usuario> leerUsuarios(String archivo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();
			while (linea != null) {
				try {
					usuarios.add(crearUsuario(linea));
				} catch (UsuarioException e) {
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
		return usuarios;
	}

	private Usuario crearUsuario(String linea) throws UsuarioException   {
		String[] lin = linea.split(",");
		if (lin.length != 4) { 
			throw new UsuarioException("Cantidad incorrecta de parametros en la linea [" + linea +  "]  ");
		}
		if (Integer.parseInt(lin[1])<= 0){
			throw new UsuarioException("El presupuesto debe ser mayor a cero");	
		} 
		Usuario usuario;
		try {
			usuario = new Usuario(Integer.parseInt(lin[0]), lin[1], Integer.parseInt(lin[2]), Double.parseDouble(lin[3]),
					TipoAtraccion.valueOf(lin[4].toUpperCase()));
			} catch (NumberFormatException e) {
			throw new UsuarioException("Uno de los parametros ingresados en la linea [" + linea +  "] no es un numero valido");
		} catch (IllegalArgumentException e) {
			throw new UsuarioException(("El parametro " + lin[3] + " ingresado en la linea [" + linea +  "]  no corresponde a un tipo de atraccion valido"));
		}
		return usuario;
	}
}
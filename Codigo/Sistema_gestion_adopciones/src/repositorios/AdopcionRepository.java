package repositorios;

import java.util.ArrayList;
import java.util.List;

import controladores.AdopcionController.CodigosRetorno;
import modelo.Adopcion;
import modelo.Animal;
import modelo.Cliente;
import modelo.Usuario;
import modelo.Visitador;
import modelo.enumeraciones.TipoUsuario;

public class AdopcionRepository {
	// In-Memory
	private static List<Adopcion> adopciones = new ArrayList<>();

	public static boolean guardar(Adopcion a) {
		for (Adopcion ad : adopciones) {
			if (ad.getID() == a.getID())
				return false;
		}
		adopciones.add(a);
		return true;
	}


	public static Adopcion obtener(int id) {
		for (Adopcion a : adopciones) {
			if (a.getID() == id)
				return a;
		}
		return null;
	}
}

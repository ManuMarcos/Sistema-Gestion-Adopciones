package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Visita;

public class VisitaRepository {

	public static List<Visita> visitas = new ArrayList<>();

	public static void guardar(Visita v) {
		visitas.add(v);
	}

}

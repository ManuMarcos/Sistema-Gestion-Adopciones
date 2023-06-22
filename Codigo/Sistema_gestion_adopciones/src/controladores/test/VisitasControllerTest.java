package controladores.test;

import controladores.VisitasController;
import vistas.VisitasView;
import vistas.enumeraciones.CliViewNames;

public class VisitasControllerTest {
	public static void main(String[] args) {
		System.err.println("VisitasControllerTest BEGIN");
		VisitasView vista = new VisitasView();
		VisitasController controlador = new VisitasController(vista);
		vista.setControlador(controlador);

		CliViewNames next = CliViewNames.STAY;

		while (next == CliViewNames.STAY) {
			next = vista.procesar();
		}

		if (next == CliViewNames.BACK) {
			System.err.println("Se fue por back");
		} else {
			System.err.println("Error inesperado");
		}
		System.err.println("VisitasControllerTest END");
	}
}

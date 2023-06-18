package controladores.test;

import controladores.AdopcionController;
import vistas.AdopcionView;
import vistas.enumeraciones.CliViewNames;

public class AdopcionControllerTest {

	public static void main(String[] args) {
		System.err.println("AdopcionControllerTest BEGIN");
		AdopcionView vista = new AdopcionView();
		AdopcionController controlador = new AdopcionController(vista);
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
		System.err.println("AdopcionControllerTest END");
	}
}

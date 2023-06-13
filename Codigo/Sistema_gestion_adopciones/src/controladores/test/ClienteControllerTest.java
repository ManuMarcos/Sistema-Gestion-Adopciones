package controladores.test;

import controladores.ClienteController;
import vistas.ClienteView;
import vistas.enumeraciones.CliViewNames;

public class ClienteControllerTest {

	public static void main(String[] args) {
		System.err.println("ClienteControllerTest BEGIN");		
		ClienteView vista = new ClienteView();
		ClienteController controlador = new ClienteController(vista);
		vista.setControlador(controlador);

		CliViewNames next = CliViewNames.STAY;

		while(next == CliViewNames.STAY) {
			next = vista.procesar();
		}
		
		if(next == CliViewNames.BACK) {
			System.err.println("Se fue por back");
		} else {
			System.err.println("Error inesperado");
		}
		System.err.println("ClienteControllerTest END");
	}
}

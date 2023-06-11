package controladores.test;

import controladores.ClienteController;
import vistas.ClienteView;

public class ClienteControllerTest {

	public static void main(String[] args) {
		ClienteView vista = new ClienteView();
		ClienteController controlador = new ClienteController(vista);
		vista.setControlador(controlador);
		vista.mostrarMenuCliente();
	}
}

package controladores.test;

import controladores.ClienteController;
import vistas.ClienteView;

public class ClienteControllerTest {

	public static void main(String[] args) {
		System.out.println("ClienteControllerTest BEGIN");		
		// TEST APP MAIN LOOP
		while(true) {
			ClienteView vista = new ClienteView();
			ClienteController controlador = new ClienteController(vista);
			vista.setControlador(controlador);
			vista.mostrarMenuCliente();
			boolean ret_code = vista.pedirOpciones();
			if(ret_code == false)
				break;
		}
		System.out.println("ClienteControllerTest END");
	}
}

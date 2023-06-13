package controladores.test;

import controladores.LoginController;
import vistas.LoginView;
import vistas.enumeraciones.CliViewNames;

public class LoginControllerTest {

	public static void main(String[] args) {
		System.err.println("LoginControllerTest BEGIN");		
		LoginView vista = new LoginView();
		LoginController controlador = new LoginController(vista);
		vista.setControlador(controlador);
		
		CliViewNames next = CliViewNames.STAY;
		while(next == CliViewNames.STAY) {
			next = vista.procesar();
		}
		if(next == CliViewNames.BACK) {
			System.err.println("Se fue por back");
		} else if(next == CliViewNames.MENU_PRINCIPAL) {
			System.err.println("Se fue por login OK");
		} else {
			System.err.println("Error inesperado");			
		}
		System.err.println("LoginControllerTest END");
	}

}

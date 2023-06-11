package controladores.test;

import controladores.LoginController;
import vistas.LoginView;

public class LoginControllerTest {

	public static void main(String[] args) {
		LoginView vista = new LoginView();
		LoginController controlador = new LoginController(vista);
		vista.setControlador(controlador);
		vista.mostrarMenuLogin();
	}

}

package controladores.test;

import controladores.LoginController;
import vistas.LoginView;

public class LoginControllerTest {

	public static void main(String[] args) {
		System.out.println("LoginControllerTest BEGIN");		
		// TEST APP MAIN LOOP
		while(true) {
			LoginView vista = new LoginView();
			LoginController controlador = new LoginController(vista);
			vista.setControlador(controlador);
			vista.mostrarMenuLogin();
			LoginView.CodigosRetorno login_ret_code = vista.pedirOpciones();
			if(login_ret_code == LoginView.CodigosRetorno.SALIR) {
				break;
			}
		}
		System.out.println("LoginControllerTest END");
	}

}

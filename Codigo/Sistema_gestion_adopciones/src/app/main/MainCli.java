 package app.main;

import controladores.LoginController;
import vistas.LoginView;
import vistas.MenuPrincipalCli;

public class MainCli {
	public static void main(String[] args) {
		MenuPrincipalCli menuPrincipal = new MenuPrincipalCli();
		menuPrincipal.mostrarCabecera();

		// APP MAIN LOOP 
		while(true){
			// TODO(Ivo): Pensar como hacer refactor. Lo armé al viejo estilo C con un loop principal y ret codes para coordinar entre pantallas... pero hay que mejorarlo.
			LoginView.CodigosRetorno login_ret_code = pedirLogin();
			if(login_ret_code == LoginView.CodigosRetorno.SALIR)
				break; // romper loop => fin
			if(login_ret_code != LoginView.CodigosRetorno.LOGIN_OK) {
				continue; // queda loopeando en menu login.
			}
			
			//TODO(Ivo): ver como guardar en la aplicación el usuario que tiene la sesión actual.
			
			menuPrincipal.mostrarMenu();
			menuPrincipal.pedirOpciones();
			// TODO: agarrar la opcion y redireccionar a otra vista.
			
			System.out.println("...FIN..."); //TODO: implementar resto del flujo.
			break; 
		}
		
	}
	private static LoginView.CodigosRetorno pedirLogin() {
		LoginView vistaLogin = new LoginView();
		LoginController controlador = new LoginController(vistaLogin);
		vistaLogin.setControlador(controlador);
		vistaLogin.mostrarMenuLogin();
		return vistaLogin.pedirOpciones();
	}
}

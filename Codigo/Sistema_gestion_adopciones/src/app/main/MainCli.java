 package app.main;

import controladores.ClienteController;
import controladores.LoginController;
import vistas.ClienteView;
import vistas.LoginView;
import vistas.MenuPrincipalCli;

public class MainCli {
	public static void main(String[] args) {
		MenuPrincipalCli menuPrincipal = new MenuPrincipalCli();
		menuPrincipal.mostrarCabecera();

		loopLogin(menuPrincipal);
		System.out.println("Cerrando el sistema...");
	}


	private static void loopLogin(MenuPrincipalCli menuPrincipal) {
		// APP MAIN LOOP 
		while(true){
			// TODO(Ivo): Pensar como hacer refactor. Lo armé al viejo estilo C con un loop principal y ret codes para coordinar entre pantallas... pero ha
			LoginView.CodigosRetorno login_ret_code = pedirLogin();
			if(login_ret_code == LoginView.CodigosRetorno.SALIR)
				break; // romper loop => fin
			if(login_ret_code != LoginView.CodigosRetorno.LOGIN_OK) {
				continue; // queda loopeando en menu login.
			}
			
			//TODO(Ivo): ver como guardar en la aplicación el usuario que tiene la sesión actual.
			
			loopMenuPpal(menuPrincipal);
		}
	}
	
	
	private static void loopMenuPpal(MenuPrincipalCli menuPrincipal) {
		//TODO(Ivo): sí, esto quedó muy feo. Hay que mover los while adentro de cada view, o cambiar por algun patron de diseño supongo.
		boolean salirMenuPpal = false;
		while(!salirMenuPpal) { 
			menuPrincipal.mostrarMenu();
			switch(menuPrincipal.pedirOpciones()) {
			case MenuPrincipalCli.MENU_ANIMALES:
				System.out.println("View no conectada aún");
				break;
			case MenuPrincipalCli.MENU_ALARMAS:
				System.out.println("View no conectada aún");
				break;
			case MenuPrincipalCli.MENU_CLIENTES:
				boolean quedarse_en_menu_cliente = true;
				while(quedarse_en_menu_cliente) {
					ClienteView vista = new ClienteView();
					ClienteController controlador = new ClienteController(vista);
					vista.setControlador(controlador);
					vista.mostrarMenuCliente();
					boolean ret_code = vista.pedirOpciones();
					quedarse_en_menu_cliente = ret_code;
				}
				break;
			case MenuPrincipalCli.MENU_ADOPCIONES:
				System.out.println("View no conectada aún");
				break;
			case MenuPrincipalCli.MENU_VISITAS:
				System.out.println("View no conectada aún");
				break;
			case MenuPrincipalCli.MENU_LOG_OUT:
			default:
				salirMenuPpal = true;
				break;
			}
		}
	}


	private static LoginView.CodigosRetorno pedirLogin() {
		//TODO. hacer esto statico, o variables de instancia para no crearlos en cada vuelta.
		LoginView vistaLogin = new LoginView();
		LoginController controlador = new LoginController(vistaLogin);
		vistaLogin.setControlador(controlador);
		vistaLogin.mostrarMenuLogin();
		return vistaLogin.pedirOpciones();
	}

}

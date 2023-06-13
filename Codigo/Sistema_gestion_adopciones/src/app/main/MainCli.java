 package app.main;

import java.util.Stack;

import controladores.ClienteController;
import controladores.LoginController;
import vistas.ClienteView;
import vistas.LoginView;
import vistas.MenuPrincipalCli;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;

public class MainCli {
	private MenuPrincipalCli vistaMenuPrincipal;
	
	private LoginView vistaLogin;
	private LoginController controladorLogin; 
	
	private ClienteView vistaCliente;
	private ClienteController controladorCliente;
	
	public Stack<ICliView> stackCliView;
	
	MainCli(){
		stackCliView = new Stack<>();
		vistaMenuPrincipal = new MenuPrincipalCli();

		//Login
		vistaLogin = new LoginView();
		controladorLogin = new LoginController(vistaLogin);
		vistaLogin.setControlador(controladorLogin);
		
		//Cliente
		vistaCliente = new ClienteView();
		controladorCliente = new ClienteController(vistaCliente);
		vistaCliente.setControlador(controladorCliente);

		stackCliView.push(vistaLogin);
	}
	
	public void mostrarCabeceraApp() {
		FormatoCli.printCabecera("Refugio - Gud Boy");
		FormatoCli.esperaTruchanga();
	}
	
	public static void main(String[] args) {
		var app = new MainCli();
		app.mostrarCabeceraApp();
		app.run();
		System.out.println("Ha salido del sistema.");
	}

	private void run() {
		while(!stackCliView.isEmpty()) {
			CliViewNames nextName = stackCliView.peek().procesar();
			ICliView next =  mapCliViewName(nextName);
			if(next != null) {
				stackCliView.push(next);
			} else if(nextName == CliViewNames.BACK) {
				stackCliView.pop();
			}
		}
	}
	
	private ICliView mapCliViewName(CliViewNames next) {
		switch(next) {
		case MENU_PRINCIPAL:
			return vistaMenuPrincipal;
		case MENU_LOGIN:
			return vistaLogin;
		case MENU_CLIENTE:
			return vistaCliente;
		case MENU_ANIMALES:
		case MENU_ALARMAS:
		case MENU_ADOPCIONES:
		case MENU_VISITAS:
			System.err.printf("View no conectada: %s%n", next.name());
			return null;
		case STAY:
		case BACK:
			return null;
		default:
			throw new RuntimeException("mapCliViewName > Invalid CliViewName:[" + next.name() + "]");
		}
	}

}

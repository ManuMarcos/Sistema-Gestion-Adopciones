package app.main;

import controladores.AdopcionController;
import controladores.AnimalController;
import controladores.ClienteController;
import controladores.LoginController;
import controladores.VisitasController;
import vistas.AdopcionView;
import vistas.AnimalView;
import vistas.ClienteView;
import vistas.LoginView;
import vistas.MenuPrincipalView;
import vistas.VentanaPrincipalAnimal;
import vistas.VisitasView;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.CliViewRunner;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;

public class MainCli extends CliViewRunner {
	private MenuPrincipalView vistaMenuPrincipal;

	private LoginView vistaLogin;
	private LoginController controladorLogin;

	private ClienteView vistaCliente;
	private ClienteController controladorCliente;

	private AdopcionView vistaAdopciones;
	private AdopcionController controladorAdopciones;
	
	private VentanaPrincipalAnimal vistaAnimales;
	private AnimalController controladorAnimales;
	
	private VisitasView vistaVisitas;
	private VisitasController controladorVisitas;

	MainCli() {
		vistaMenuPrincipal = new MenuPrincipalView();

		// Login
		vistaLogin = new LoginView();
		controladorLogin = new LoginController(vistaLogin);
		vistaLogin.setControlador(controladorLogin);

		// Cliente
		vistaCliente = new ClienteView();
		controladorCliente = new ClienteController(vistaCliente);
		vistaCliente.setControlador(controladorCliente);

		// Adopciones
		vistaAdopciones = new AdopcionView();
		controladorAdopciones = new AdopcionController(vistaAdopciones);
		vistaAdopciones.setControlador(controladorAdopciones);

		//Animales
		vistaAnimales = new VentanaPrincipalAnimal();
		controladorAnimales = new AnimalController(vistaAnimales);
		vistaAnimales.setController(controladorAnimales);
		
		//Visitas
		vistaVisitas = new VisitasView();
		controladorVisitas = new VisitasController(vistaVisitas);
		vistaVisitas.setControlador(controladorVisitas);

		
		setFirstView(vistaLogin);
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

	@Override
	protected ICliView mapCliViewName(CliViewNames next) {
		switch (next) {
		case MENU_PRINCIPAL:
			return vistaMenuPrincipal;
		case MENU_LOGIN:
			return vistaLogin;
		case MENU_CLIENTE:
			return vistaCliente;
		case MENU_ADOPCIONES:
			return vistaAdopciones;
		case MENU_ANIMALES:
			
		case MENU_ALARMAS:
		case MENU_VISITAS:
			System.err.printf("View no conectada: %s%n", next.name());
			FormatoCli.esperaTruchanga();
			return null;
		case STAY:
		case BACK:
			return null;
		default:
			throw new RuntimeException("mapCliViewName > Invalid CliViewName:[" + next.name() + "]");
		}
	}

}

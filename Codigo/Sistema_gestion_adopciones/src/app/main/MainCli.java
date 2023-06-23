package app.main;

import java.time.LocalDate;

import controladores.AdopcionController;
import controladores.AlarmaController;
import controladores.AnimalController;
import controladores.ClienteController;
import controladores.LoginController;
import controladores.VisitasController;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import vistas.AdopcionView;
import vistas.AlarmaView;
import vistas.AnimalView;
import vistas.ClienteView;
import vistas.LoginView;
import vistas.MenuPrincipalView;
import vistas.VentanaPrincipalAnimal;
import vistas.VentanaDatosAnimal;
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
	
	private VentanaPrincipalAnimal ventanaPrincipalAnimal;
	private VentanaDatosAnimal ventanaDatosAnimal;
	private AnimalController controladorAnimal;
	
	private VisitasView vistaVisitas;
	private VisitasController controladorVisitas;
	
	private AlarmaView vistaAlarmas;
	private AlarmaController controladorAlarmas;

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
		controladorAnimal =  new AnimalController();
		ventanaPrincipalAnimal = new VentanaPrincipalAnimal();
		ventanaDatosAnimal = new VentanaDatosAnimal();
		
		ventanaPrincipalAnimal.setController(controladorAnimal);
		ventanaDatosAnimal.setController(controladorAnimal);
		
		controladorAnimal.setVentanaPrincipal(ventanaPrincipalAnimal);
		controladorAnimal.setVentanaRegistro(ventanaDatosAnimal);
	
		
		//Visitas
		vistaVisitas = new VisitasView();
		controladorVisitas = new VisitasController(vistaVisitas);
		vistaVisitas.setControlador(controladorVisitas);
		
		//Alarmas
		vistaAlarmas = new AlarmaView(controladorAnimal);
		controladorAlarmas= new AlarmaController(vistaAlarmas);
		vistaAlarmas.setController(controladorAlarmas);
		
		controladorAnimal.cargarAnimal(new AnimalDto(199,5000,LocalDate.of(2010, 6, 30), "Perro",EstadoAnimal.EN_TRATAMIENTO, TipoAnimal.DOMESTICO));
		
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
			controladorAnimal.mostrarVentanaPrincipal();
			return ventanaPrincipalAnimal;
		case MENU_ALARMAS:
			return vistaAlarmas;
		case MENU_VISITAS:
			return vistaVisitas;
		case STAY:
		case BACK:
			return null;
		default:
			throw new RuntimeException("mapCliViewName > Invalid CliViewName:[" + next.name() + "]");
		}
	}

}

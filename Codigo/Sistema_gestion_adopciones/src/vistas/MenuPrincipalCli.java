package vistas;

import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class MenuPrincipalCli implements ICliView {
	private final static int MENU_ANIMALES   = 1;
	private final static int MENU_ALARMAS    = 2;
	private final static int MENU_CLIENTES   = 3;
	private final static int MENU_ADOPCIONES = 4;
	private final static int MENU_VISITAS    = 5;
	private final static int MENU_LOG_OUT    = 6;
	
	public void mostrarCabecera() {
		FormatoCli.printCabecera("Refugio - Gud Boy");
		FormatoCli.esperaTruchanga();
	}

	private static final String[] opciones = {"Animales", "Alarmas", "Clientes", "Adopciones", "Visitas", "Log out"};
	
	public void mostrarMenu() {
		FormatoCli.printCabecera("Refugio - Gud Boy");
		FormatoCli.printOpciones(opciones);
	}

	public int pedirOpciones() {
		return IngresoCli.solicitarOpcion(opciones.length);

	}

	@Override
	public CliViewNames procesar() {
		mostrarMenu();
		int opcion = pedirOpciones();
		switch(opcion) {
		case MENU_ANIMALES:
			return CliViewNames.MENU_ANIMALES;
		case MENU_ALARMAS:
			return CliViewNames.MENU_ALARMAS;
		case MENU_CLIENTES:
			return CliViewNames.MENU_CLIENTE;
		case MENU_ADOPCIONES:
			return CliViewNames.MENU_ADOPCIONES;
		case MENU_VISITAS:
			return CliViewNames.MENU_VISITAS;
		case MENU_LOG_OUT:
			return CliViewNames.BACK;
		default:
			throw new RuntimeException("procesar > opcion invalida :[" + opcion + "]");
		}
	}

}

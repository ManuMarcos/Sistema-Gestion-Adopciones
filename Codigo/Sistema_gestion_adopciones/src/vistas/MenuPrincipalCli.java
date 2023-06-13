package vistas;

import vistas.utils.FormatoCli;
import vistas.utils.IngresoCli;

public class MenuPrincipalCli {	
	
	//TODO: pensar mejor esto...
	public final static int MENU_ANIMALES   = 0;
	public final static int MENU_ALARMAS    = 1;
	public final static int MENU_CLIENTES   = 2;
	public final static int MENU_ADOPCIONES = 3;
	public final static int MENU_VISITAS    = 4;
	public final static int MENU_LOG_OUT    = 5;
	
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
		// TODO redireccion a otros modulos.
		return IngresoCli.solicitarOpcion(opciones.length);

	}

}

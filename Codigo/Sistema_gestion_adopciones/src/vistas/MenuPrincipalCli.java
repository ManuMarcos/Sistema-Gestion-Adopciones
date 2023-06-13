package vistas;

import vistas.utils.FormatoCli;
import vistas.utils.IngresoCli;

public class MenuPrincipalCli {	
	
	public void mostrarCabecera() {
		FormatoCli.printCabecera("Refugio - Gud Boy");
		FormatoCli.esperaTruchanga();
	}

	private static final String[] opciones = {"Animales", "Alarmas", "Clientes", "Adopciones", "Visitas", "Log out"};
	
	public void mostrarMenu() {
		FormatoCli.printCabecera("Refugio - Gud Boy");
		FormatoCli.printOpciones(opciones);
	}

	public void pedirOpciones() {
		// TODO redireccion a otros modulos.
		IngresoCli.solicitarOpcion(opciones.length);

	}

}

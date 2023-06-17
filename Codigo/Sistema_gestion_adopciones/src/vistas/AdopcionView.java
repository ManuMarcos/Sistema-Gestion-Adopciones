package vistas;

import controladores.AdopcionController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AdopcionView implements ICliView {
	private AdopcionController controlador;

	private static final String[] opciones = { "Nueva", "Consultar", "Atrás" };
	private static final int opcion_Nueva = 1;
	private static final int opcion_Consultar = 2;
	private static final int opcion_Atras = 3;

	private void mostrarMenuAdopciones() {
		FormatoCli.printCabecera("Menú Adopciones");
		FormatoCli.printOpciones(opciones);
	}

	private AdopcionController.CodigosRetorno pedirOpcionesYProcesar() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		/* TODO: implementar
		case opcion_Nueva:
			return xxx();
		case opcion_Consultar:
			return yyy();
		*/
		case opcion_Atras:
			return AdopcionController.CodigosRetorno.SALIR;
		default:
			throw new RuntimeException("pedirOpcionesYProcesar > opcion invalida :[" + opcion + "]");
		}
	}

	public void setControlador(AdopcionController controlador) {
		this.controlador = controlador;
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuAdopciones();
		AdopcionController.CodigosRetorno retCode = pedirOpcionesYProcesar();
		if (retCode == AdopcionController.CodigosRetorno.SALIR)
			return CliViewNames.BACK;
		return CliViewNames.STAY;
	}
}

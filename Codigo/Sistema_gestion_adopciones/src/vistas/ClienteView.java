package vistas;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;
import java.util.ArrayList;

public class ClienteView implements ICliView {
	private ClienteController controlador;

	private enum CodigosRetorno {
		ALTA_CLIENTE_OK, ALTA_CLIENTE_ERROR, BUSCAR_CLIENTE_OK, BUSCAR_CLIENTE_NOT_FOUND, ATRAS
	}

	private static final String[] opciones = { "Alta", "Búsqueda", "Atrás" };
	private static final int opcion_Alta = 1;
	private static final int opcion_Busqueda = 2;
	private static final int opcion_Atras = 3;

	private void mostrarMenuCliente() {
		FormatoCli.printCabecera("Menú Cliente");
		FormatoCli.printOpciones(opciones);
	}

	private CodigosRetorno pedirOpcionesYProcesar() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		case opcion_Alta:
			return mostrarAltaCliente();
		case opcion_Busqueda:
			return mostrarBusquedaCliente();
		case opcion_Atras:
			return CodigosRetorno.ATRAS;
		default:
			throw new RuntimeException("pedirOpciones > opcion invalida :[" + opcion + "]");
		}
	}

	public CodigosRetorno mostrarAltaCliente() {
		FormatoCli.printCabecera("Alta Cliente");
		ClienteDto clienteDatos = new ClienteDto();

		clienteDatos.documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");
		clienteDatos.nombre = IngresoCli.solicitarStringNoNulo("Ingrese nombre: ");
		clienteDatos.apellido = IngresoCli.solicitarStringNoNulo("Ingrese apellido: ");
		clienteDatos.email = IngresoCli.solicitarStringNoNulo("Ingrese email: ");
		clienteDatos.estadoCivil = IngresoCli.solicitarStringNoNulo("Ingrese estado civil: ");
		clienteDatos.telefono = IngresoCli.solicitarStringNoNulo("Ingrese teléfono: ");
		clienteDatos.setOcupacion(0); // TODO(ivo): completar el resto zzz
		clienteDatos.tieneOtrasMascotas = false;
		clienteDatos.motivoAdopta = "porque sí";
		clienteDatos.animalesDeInteres = new ArrayList<String>();
		clienteDatos.animalesDeInteres.add("Gato");
		clienteDatos.animalesDeInteres.add("Perro");

		boolean res = controlador.registrarCliente(clienteDatos);
		if (res) {
			System.out.println("Cliente registrado exitosamente");
			return CodigosRetorno.ALTA_CLIENTE_OK;
		} else {
			System.out.println("Error al registrar.");
			return CodigosRetorno.ALTA_CLIENTE_ERROR;
		}
	}

	public CodigosRetorno mostrarBusquedaCliente() {
		FormatoCli.printCabecera("Buscar Cliente");
		String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");

		ClienteDto cliente = controlador.buscarCliente(documento);
		if (cliente == null) {
			System.out.println("Usuario no encontrado.");
			return CodigosRetorno.BUSCAR_CLIENTE_NOT_FOUND;
		} else {
			mostrarDatosCliente(cliente);
			return CodigosRetorno.BUSCAR_CLIENTE_OK;
		}
	}

	private void mostrarDatosCliente(ClienteDto cliente) {
		// TODO mostrar datos del cliente
		System.out.println("Nombre: " + cliente.nombre);
		System.out.println("Todo: lo demas...");
	}

	public void setControlador(ClienteController controlador) {
		this.controlador = controlador;
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuCliente();
		CodigosRetorno retCode = pedirOpcionesYProcesar();
		if (retCode == CodigosRetorno.ATRAS) {
			return CliViewNames.BACK;
		} else {
			return CliViewNames.STAY;
		}
	}

}

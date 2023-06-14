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

	private static final String[] opciones = { "Alta", "Búsqueda", "Atrás" };
	private static final int opcion_Alta = 1;
	private static final int opcion_Busqueda = 2;
	private static final int opcion_Atras = 3;

	private void mostrarMenuCliente() {
		FormatoCli.printCabecera("Menú Cliente");
		FormatoCli.printOpciones(opciones);
	}

	private ClienteController.CodigosRetorno pedirOpcionesYProcesar() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		case opcion_Alta:
			return mostrarAltaCliente();
		case opcion_Busqueda:
			return mostrarBusquedaCliente();
		case opcion_Atras:
			return ClienteController.CodigosRetorno.ATRAS;
		default:
			throw new RuntimeException("pedirOpciones > opcion invalida :[" + opcion + "]");
		}
	}

	public ClienteController.CodigosRetorno mostrarAltaCliente() {
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

		ClienteController.CodigosRetorno res = controlador.registrarCliente(clienteDatos);
		if (res == ClienteController.CodigosRetorno.ALTA_CLIENTE_OK) {
			System.out.println("Cliente registrado exitosamente");
		} else {
			System.out.println("Error al registrar.");
		}
		return res;
	}

	public ClienteController.CodigosRetorno mostrarBusquedaCliente() {
		FormatoCli.printCabecera("Buscar Cliente");
		String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");

		ClienteDto cliente = controlador.buscarCliente(documento);
		if (cliente == null) {
			System.out.println("Usuario no encontrado.");
			return ClienteController.CodigosRetorno.BUSCAR_CLIENTE_NOT_FOUND;
		} else {
			mostrarDatosCliente(cliente);
			return ClienteController.CodigosRetorno.BUSCAR_CLIENTE_OK;
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
		ClienteController.CodigosRetorno retCode = pedirOpcionesYProcesar();
		if (retCode == ClienteController.CodigosRetorno.ATRAS) {
			return CliViewNames.BACK;
		} else {
			return CliViewNames.STAY;
		}
	}

}

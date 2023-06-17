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
		ClienteDto clienteDatos = cargarDatosCliente();
		ClienteController.CodigosRetorno res = controlador.registrarCliente(clienteDatos);
		if (res == ClienteController.CodigosRetorno.ALTA_CLIENTE_OK) {
			System.out.println("Cliente registrado exitosamente");
		} else {
			System.out.println("Error al registrar.");
		}
		return res;
	}

	private ClienteDto cargarDatosCliente() {
		ClienteDto clienteDatos = new ClienteDto();
		clienteDatos.documento = IngresoCli.solicitarStringNumericoNulo("Ingrese documento(numerico): ");
		clienteDatos.nombre = IngresoCli.solicitarStringNoNulo("Ingrese nombre: ");
		clienteDatos.apellido = IngresoCli.solicitarStringNoNulo("Ingrese apellido: ");
		clienteDatos.email = IngresoCli.solicitarEmail("Ingrese email: ");
		clienteDatos.estadoCivil = IngresoCli.solicitarStringNoNulo("Ingrese estado civil: ");
		clienteDatos.telefono = IngresoCli.solicitarStringNoNulo("Ingrese teléfono: ");
		clienteDatos.setOcupacion(0); // meh
		clienteDatos.tieneOtrasMascotas = IngresoCli.solicitarSiNo("Tiene otras mascotas? (Y/N): ");
		clienteDatos.motivoAdopta = IngresoCli.solicitarStringNoNulo("Ingrese motivo adopta: ");
		clienteDatos.animalesDeInteres = new ArrayList<String>();
		{
			boolean fin = false;
			do {
				String inputAnimalesInteres = IngresoCli
						.solicitarStringNoNulo("Ingrese que animales le interesan (FIN para terminar ingreso): ");
				if (inputAnimalesInteres.equals("FIN"))
					fin = true;
				else
					clienteDatos.animalesDeInteres.add(inputAnimalesInteres);
			} while (!fin);
		}
		return clienteDatos;
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
			System.out.println("Usuario encontrado.");
			return ClienteController.CodigosRetorno.BUSCAR_CLIENTE_OK;
		}
	}

	private static void mostrarDatosCliente(ClienteDto cliente) {
		System.out.println("  Nombre: " + cliente.nombre);
		System.out.println("  Apellido: " + cliente.apellido);
		System.out.println("  Email: " + cliente.email);		
		System.out.println("  Telefono: " + cliente.telefono);
		System.out.println("  Tiene otras mascotas: " + (cliente.tieneOtrasMascotas ? "Si" : "No"));
		System.out.println("  Animales de interes: " );
		for(String animal : cliente.animalesDeInteres) {
			System.out.println("    - " + animal);
		}
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

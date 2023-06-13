package vistas;

import java.util.List;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import modelo.enumeraciones.Ocupacion;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;
import java.util.ArrayList;

public class ClienteView implements ICliView {
	private ClienteController controlador;
	private static final String[] opciones = { "Alta", "Búsqueda", "Atrás" };

	public void mostrarMenuCliente() {
		FormatoCli.printCabecera("Menú Cliente");
		FormatoCli.printOpciones(opciones);
	}

	public boolean pedirOpciones() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		case 1:
			mostrarAltaCliente();
			return true; // TODO: mejorar esto. true=quedarse.
		case 2:
			mostrarBusquedaCliente();
			return true;
		case 3:
		default:
			return false;
		}
	}

	public void mostrarAltaCliente() {
		FormatoCli.printCabecera("Alta Cliente");
		ClienteDto clienteDatos = new ClienteDto();

		clienteDatos.documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");
		clienteDatos.nombre = IngresoCli.solicitarStringNoNulo("Ingrese nombre: ");
		clienteDatos.apellido = IngresoCli.solicitarStringNoNulo("Ingrese apellido: ");
		clienteDatos.email = IngresoCli.solicitarStringNoNulo("Ingrese email: ");
		clienteDatos.estadoCivil = IngresoCli.solicitarStringNoNulo("Ingrese estado civil: ");
		clienteDatos.telefono = IngresoCli.solicitarStringNoNulo("Ingrese teléfono: ");
		clienteDatos.setOcupacion(0); // TODO: completar el resto zzz
		clienteDatos.tieneOtrasMascotas = false;
		clienteDatos.motivoAdopta = "porque sí";
		clienteDatos.animalesDeInteres = new ArrayList<String>();
		clienteDatos.animalesDeInteres.add("Gato");
		clienteDatos.animalesDeInteres.add("Perro");

		boolean res = controlador.registrarCliente(clienteDatos);
		if (res) {
			System.out.println("Cliente registrado exitosamente");
		} else {
			System.out.println("Error al registrar.");
		}
		mostrarMenuCliente();
	}

	public void mostrarBusquedaCliente() {
		FormatoCli.printCabecera("Buscar Cliente");
		String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");

		ClienteDto cliente = controlador.buscarCliente(documento);
		if (cliente == null) {
			System.out.println("Usuario no encontrado.");
		} else {
			mostrarDatosCliente(cliente);
		}
		mostrarMenuCliente();
	}

	private void mostrarDatosCliente(ClienteDto cliente) {
		// TODO mostrar datos del cliente
		System.out.println("Nombre: " + cliente.nombre);
	}

	public void setControlador(ClienteController controlador) {
		this.controlador = controlador;
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuCliente();
		boolean ret_code = pedirOpciones();
		if(ret_code) {
			return CliViewNames.STAY;
		} else {
			return CliViewNames.BACK;
		}
	}

}

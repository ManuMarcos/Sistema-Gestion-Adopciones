package vistas;

import java.util.List;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import modelo.enumeraciones.Ocupacion;
import vistas.utils.FormatoCli;
import vistas.utils.IngresoCli;
import java.util.ArrayList;

public class ClienteView {
	private ClienteController controlador;

	public void mostrarMenuCliente() {
		FormatoCli.printCabecera("Menú Cliente");
		String[] opciones = { "Alta", "Búsqueda", "Atrás" };
		FormatoCli.printOpciones(opciones);
		deMenuA(IngresoCli.solicitarOpcion(opciones.length));
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
		clienteDatos.setOcupacion(0); //TODO: completar el resto zzz
		clienteDatos.tieneOtrasMascotas = false;
		clienteDatos.motivoAdopta = "porque sí";
		clienteDatos.animalesDeInteres = new ArrayList<String>();
		clienteDatos.animalesDeInteres.add("Gato");
		clienteDatos.animalesDeInteres.add("Perro");
		
		boolean res = controlador.registrarCliente(clienteDatos);
		if(res) {
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
		if(cliente == null) {
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

	private void irAHome() {
		// TODO: ir a la siguiente pantalla en el flujo...
		System.out.print("TODO: ir a la siguiente pantalla en el flujo...");
	}

	private void deMenuA(int opcion) {
		switch (opcion) {
		case 1:
			mostrarAltaCliente();
			break;
		case 2:
			mostrarBusquedaCliente();
			break;
		case 3: // TODO: flujo de navegacion hacia atras.
			System.out.print("chau");
		}
	}

	public void setControlador(ClienteController controlador) {
		this.controlador = controlador;
	}

}

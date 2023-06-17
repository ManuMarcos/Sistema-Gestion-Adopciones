package vistas;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;
import java.util.ArrayList;
import java.util.List;

public class ClienteView implements ICliView {

	class OptionAltaCliente implements ICliOption {
		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Alta Cliente");
			ClienteDto clienteDatos = cargarDatosCliente();
			ClienteController.CodigosRetorno res = controlador.registrarCliente(clienteDatos);
			if (res == ClienteController.CodigosRetorno.ALTA_CLIENTE_OK) {
				System.out.println("Cliente registrado exitosamente");
			} else {
				System.out.println("Error al registrar.");
			}
			return CliViewNames.STAY;
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
	}

	class OptionBuscarCliente implements ICliOption {

		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Buscar Cliente");
			String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");

			ClienteDto cliente = controlador.buscarCliente(documento);
			if (cliente == null) {
				System.out.println("Usuario no encontrado.");
			} else {
				System.out.println("Usuario encontrado.");
				mostrarDatosCliente(cliente);
			}
			return CliViewNames.STAY;
		}

		private static void mostrarDatosCliente(ClienteDto cliente) {
			System.out.println("  Nombre: " + cliente.nombre);
			System.out.println("  Apellido: " + cliente.apellido);
			System.out.println("  Email: " + cliente.email);
			System.out.println("  Telefono: " + cliente.telefono);
			System.out.println("  Tiene otras mascotas: " + (cliente.tieneOtrasMascotas ? "Si" : "No"));
			System.out.println("  Animales de interes: ");
			for (String animal : cliente.animalesDeInteres) {
				System.out.println("    - " + animal);
			}
		}
	}

	//

	private static final String[] nombresOpciones = { "Alta", "Búsqueda", "Atrás" };
	private List<ICliOption> opciones;
	private ClienteController controlador;

	public void setControlador(ClienteController controlador) {
		this.controlador = controlador;
	}

	public ClienteView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionAltaCliente());
		opciones.add(new OptionBuscarCliente());
		opciones.add(new ICliOption.OptionBack());
	}

	@Override
	public CliViewNames procesar() {
		FormatoCli.printCabecera("Menú Cliente");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

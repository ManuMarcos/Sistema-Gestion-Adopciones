package vistas;

import java.util.ArrayList;
import java.util.List;

import controladores.AdopcionController;
import controladores.AdopcionController.CodigosRetorno;
import modelo.Usuario;
import modelo.dto.AdopcionDto;
import modelo.dto.AltaAdopcionDto;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AdopcionView implements ICliView {

	private void mostrarInfoAdopcion(AdopcionDto data) {
		// TODO: se podria hacer una busqueda por cada id para traer mas datos...
		System.out.println("  Documento Cliente: " + data.documentoCliente);
		System.out.println("  Nro Ingreso Animal: " + data.nroIngresoAnimal);
	}

	class OptionNuevaAdopcion implements ICliOption {
		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Nueva Adopción");
			AltaAdopcionDto data = new AltaAdopcionDto();
			data.usuarioDelVisitador = IngresoCli.solicitarStringNoNulo("Ingrese el usuario del visitador: ");
			data.documentoCliente = IngresoCli.solicitarStringNumericoNulo("Documento del cliente: ");
			data.nroIngresoAnimal = Integer.parseInt(IngresoCli.solicitarStringNoNulo("Ingrese numero de ingreso del Animal: "));
			var ret = controlador.crearAdopcion(data);
			mostrarMensajeSegunRta(ret);
			return CliViewNames.STAY;
		}

		private void mostrarMensajeSegunRta(CodigosRetorno ret) throws RuntimeException {
			switch (ret) {
			case ERROR_ALTA_ANIMAL_NO_ADOPTABLE:
				System.out.println("Animal no es apto para adopcion.");
				break;
			case ERROR_ALTA_ANIMAL_NO_ENCONTRADO:
				System.out.println("Animal no encontrado.");
				break;
			case ERROR_ALTA_CLIENTE_EXCEDE_LIMITE_ADOPCIONES:
				System.out.println("Cliente no puede adoptar más animales acá.");
				break;
			case ERROR_ALTA_CLIENTE_NO_ENCONTRADO:
				System.out.println("Cliente no encontrado.");
				break;
			case ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO:
				System.out.println("Visitador no encontrado");
				break;
			case ALTA_OK:
				System.out.println("Adopción creada exitosamente");
				break;
			default:
				throw new RuntimeException("OptionNuevaAdopcion - codigo de respuesta no mapeado ");
			}
		}
	}

	class OptionConsultarAdopcionesPorCliente implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");
			var adopciones = controlador.buscarAdopcionesPorCliente(documento);
			if (adopciones.isEmpty()) {
				System.out.println("No se encontraron adopciones para ese cliente.");
			} else {
				System.out.println("Adopciones encontradas: ");
				System.out.println("---");
				for (AdopcionDto a : adopciones) {
					mostrarInfoAdopcion(a);
					System.out.println("---");
				}
			}
			return CliViewNames.STAY;
		}
	}

	class OptionConsultarAdopcionPorAnimal implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String nroIngresoAnimal = IngresoCli.solicitarStringNoNulo("Ingrese Nro ingreso del animal: ");
			var adopcion = controlador.buscarAdopcionesPorAnimal(nroIngresoAnimal);
			if (adopcion == null)
				System.out.println("Adopcion no encontrada");
			else {
				System.out.println("Adopcion encontrada:");
				mostrarInfoAdopcion(adopcion);
			}
			return CliViewNames.STAY;
		}
	}

	//

	private static final String[] nombresOpciones = { "Nueva", "Consultar por Cliente", "Consultar por Animal",
			"Atrás" };
	private List<ICliOption> opciones;
	private AdopcionController controlador;

	public void setControlador(AdopcionController controlador) {
		this.controlador = controlador;
	}

	public AdopcionView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionNuevaAdopcion());
		opciones.add(new OptionConsultarAdopcionesPorCliente());
		opciones.add(new OptionConsultarAdopcionPorAnimal());
		opciones.add(new ICliOption.OptionBack());
	}

	@Override
	public CliViewNames procesar() {
		this.controlador.enviarNotificaciones(Usuario.getUsuarioActivo());
		FormatoCli.printCabecera("Menú Adopciones");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

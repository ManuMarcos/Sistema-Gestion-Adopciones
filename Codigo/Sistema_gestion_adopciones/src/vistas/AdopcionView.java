package vistas;

import java.util.ArrayList;
import java.util.List;

import controladores.AdopcionController;
import modelo.dto.AdopcionDto;
import modelo.dto.AltaAdopcionDto;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AdopcionView implements ICliView {

	class OptionNuevaAdopcion implements ICliOption {
		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Nueva Adopción");
			AltaAdopcionDto data = new AltaAdopcionDto();
			data.usuarioDelVisitador = IngresoCli.solicitarStringNoNulo("Ingrese el usuario del visitador: ");
			data.documentoCliente = IngresoCli.solicitarStringNumericoNulo("Documento del cliente: ");
			data.idAnimal = Integer.parseInt(IngresoCli.solicitarStringNoNulo("Ingrese identificador del Animal: "));
			var ret = controlador.crearAdopcion(data);
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
			return CliViewNames.STAY;
		}
	}

	class OptionConsultarAdopcionPorID implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String id = IngresoCli.solicitarStringNoNulo("Ingrese id de la adopcion: ");
			AdopcionDto adopcion = controlador.buscarAdopcionPorID(id);
			if (adopcion == null)
				System.out.println("Adopcion no encontrada");
			System.out.println("Adopcion encontrada");
			mostrarInfoAdopcion(adopcion);
			return CliViewNames.STAY;
		}

		private void mostrarInfoAdopcion(AdopcionDto data) {
			// TODO: se podria hacer una busqueda por cada id para traer mas datos...
			System.out.println("  Documento Cliente: " + data.documentoCliente);
			System.out.println("  Id Animal: " + data.idAnimal);
		}
	}

	class OptionConsultarAdopcionesPorCliente implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String documento = IngresoCli.solicitarStringNoNulo("Ingrese documento: ");
			System.out.println("TODO: no implementado aún");
			return CliViewNames.STAY;
		}
	}

	class OptionConsultarAdopcionPorAnimal implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String idAnimal = IngresoCli.solicitarStringNoNulo("Ingrese id animal: ");
			System.out.println("TODO: no implementado aún");
			return CliViewNames.STAY;
		}
	}

	//

	private static final String[] nombresOpciones = { "Nueva", "Consultar por ID", "Consultar por Cliente",
			"Consultar por Animal", "Atrás" };
	private List<ICliOption> opciones;
	private AdopcionController controlador;

	public void setControlador(AdopcionController controlador) {
		this.controlador = controlador;
	}

	public AdopcionView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionNuevaAdopcion());
		opciones.add(new OptionConsultarAdopcionPorID());
		opciones.add(new OptionConsultarAdopcionesPorCliente());
		opciones.add(new OptionConsultarAdopcionPorAnimal());
		opciones.add(new ICliOption.OptionBack());
	}

	@Override
	public CliViewNames procesar() {
		FormatoCli.printCabecera("Menú Adopciones");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

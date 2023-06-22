package vistas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controladores.VisitasController;
import modelo.dto.EncuestaDto;
import modelo.dto.VisitaDto;
import modelo.enumeraciones.Calificacion;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class VisitasView implements ICliView {

	class OptionCargarVisita implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String idAnimal = IngresoCli.solicitarStringNumericoNulo("Ingrese id animal: ");
			var ret = controlador.validarExisteAdopcion(idAnimal);
			if (ret == VisitasController.CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE) {
				System.out.println("No se encontró el animal.");
			} else if (ret == VisitasController.CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE) {
				System.out.println("El animal no se encuentra adoptado.");
			} else {
				VisitasController.CodigosRetorno retvis = cargarVisita(idAnimal);
				if (retvis == VisitasController.CodigosRetorno.ADOPCION_CARGADA) {
					System.out.println("Visita cargada exitosamente.");
				} else {
					System.out.println("Error al cargar la visita.");
				}
			}
			return CliViewNames.STAY;
		}

		private VisitasController.CodigosRetorno cargarVisita(String idAnimal) {
			System.out.println("Debera completar una breve encuesta:");
			System.out.println("Valoraciones: 3=bueno | 2=regular | 1=malo");
			System.out.print("Estado del animal. ");
			int estadoAnimal = IngresoCli.solicitarOpcion(3);
			System.out.print("Estado del ambiente. ");
			int estadoAmbiente = IngresoCli.solicitarOpcion(3);
			System.out.print("Estado de limpieza. ");
			int estadoLimpieza = IngresoCli.solicitarOpcion(3);

			VisitaDto visita = new VisitaDto();
			visita.encuesta = new EncuestaDto(Calificacion.toCalificacion(estadoAnimal),
					Calificacion.toCalificacion(estadoAmbiente), Calificacion.toCalificacion(estadoLimpieza));
			visita.fecha = new Date();
			visita.idAnimal = idAnimal;
			return controlador.cargarVisita(visita);
		}
	}

	class OptionConsultarVisitas implements ICliOption {
		@Override
		public CliViewNames doAction() {
			String idAnimal = IngresoCli.solicitarStringNumericoNulo("Ingrese id animal: ");
			var ret = controlador.validarExisteAdopcion(idAnimal);
			if (ret == VisitasController.CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE) {
				System.out.println("No se encontró el animal.");
			} else if (ret == VisitasController.CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE) {
				System.out.println("El animal no se encuentra adoptado.");
			} else {
				System.out.println("Visitas:");
				var visitasDto = controlador.getVisitas(idAnimal);
				for(VisitaDto visita : visitasDto) {
					printVisita(visita);
				}
			}
			return CliViewNames.STAY;
		}

		private void printVisita(VisitaDto visita) {
			System.out.println("> " + visita.fecha.toString());
			System.out.println("  + Calificacion Animal: " + visita.encuesta.getEstadoAnimal().toString());
			System.out.println("  + Calificacion Ambiente: " + visita.encuesta.getAmbiente().toString());
			System.out.println("  + Calificacion Limpieza: " + visita.encuesta.getLimpiezaLugar().toString());
		}
	}

	//

	private static final String[] nombresOpciones = { "Cargar Visita", "Consultar Visitas", "Atrás" };
	private List<ICliOption> opciones;
	private VisitasController controlador;

	public void setControlador(VisitasController controlador) {
		this.controlador = controlador;
	}

	public VisitasView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionCargarVisita());
		opciones.add(new OptionConsultarVisitas());
		opciones.add(new ICliOption.OptionBack());
	}

	@Override
	public CliViewNames procesar() {
		FormatoCli.printCabecera("Menú Visitas");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

package controladores;

import modelo.Adopcion;
import modelo.Animal;
import modelo.Visita;
import modelo.dto.VisitaDto;
import vistas.VisitasView;

public class VisitasController {
	public enum CodigosRetorno {
		ERROR_ANIMAL_NO_EXISTENTE, ERROR_ADOPCION_NO_EXISTENTE, ADOPCION_ENCONTRADA, ADOPCION_CARGADA
	}

	private VisitasView vistaVisitas;

	public VisitasController(VisitasView vistaVisitas) {
		this.vistaVisitas = vistaVisitas;
	}

	public CodigosRetorno validarExisteAdopcion(String idAnimal) {
		var animal = Animal.getAnimalHardCodeado(Integer.parseInt(idAnimal));

		if (animal == null) {
			return CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE;
		}
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE;

		return CodigosRetorno.ADOPCION_ENCONTRADA;
	}

	public CodigosRetorno cargarVisita(VisitaDto datosVisita) {
		var animal = Animal.getAnimalHardCodeado(Integer.parseInt(datosVisita.idAnimal));
		if (animal == null) {
			return CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE;
		}
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE;

		Visita v = new Visita(datosVisita.fecha, datosVisita.encuesta, adopcion);
		Visita.registrar(v, animal);
		return CodigosRetorno.ADOPCION_CARGADA;
	}

}

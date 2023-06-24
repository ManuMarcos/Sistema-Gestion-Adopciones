package controladores;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;

import modelo.Adopcion;
import modelo.Animal;
import modelo.Visita;
import modelo.dto.VisitaDto;
import vistas.VisitasView;

public class VisitasController {
	public enum CodigosRetorno {
		ERROR_ANIMAL_NO_EXISTENTE, ERROR_ADOPCION_NO_EXISTENTE, ADOPCION_ENCONTRADA, ADOPCION_CARGADA,
		ERROR_SEGUIMIENTO_FINALIZADO_PREVIAMENTE, SEGUIMIENTO_FINALIZADO
	}

	private VisitasView vistaVisitas;

	public VisitasController(VisitasView vistaVisitas) {
		this.vistaVisitas = vistaVisitas;
	}

	public CodigosRetorno validarExisteAdopcion(String nroIngresoAnimal) {
		Animal animal = new Animal().buscarAnimal(Integer.parseInt(nroIngresoAnimal));
		if (animal == null) {
			return CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE;
		}
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE;

		return CodigosRetorno.ADOPCION_ENCONTRADA;
	}

	public CodigosRetorno cargarVisita(VisitaDto datosVisita) {
		Animal animal = new Animal().buscarAnimal(Integer.parseInt(datosVisita.idAnimal));
		if (animal == null) {
			return CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE;
		}
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE;
		if(!adopcion.getContinuarConVisitas())
			return CodigosRetorno.ERROR_SEGUIMIENTO_FINALIZADO_PREVIAMENTE;
		Visita v = new Visita(datosVisita.fecha, datosVisita.encuesta, adopcion);
		Visita.registrar(v, animal);
		return CodigosRetorno.ADOPCION_CARGADA;
	}

	public List<VisitaDto> getVisitas(String nroIngresoAnimal) {
		List<VisitaDto> visitasDto = new ArrayList<>();
		Animal animal = new Animal().buscarAnimal(Integer.parseInt(nroIngresoAnimal));
		if (animal == null) {
			return visitasDto;
		}
		var visitas = animal.getVisitasDeFicha();
		for (Visita v : visitas) {
			visitasDto.add(v.toDto());
		}
		return visitasDto;
	}

	public VisitasController.CodigosRetorno terminarSeguimiento(String nroIngresoAnimal) {
		Animal animal = new Animal().buscarAnimal(Integer.parseInt(nroIngresoAnimal));
		if (animal == null) {
			return CodigosRetorno.ERROR_ANIMAL_NO_EXISTENTE;
		}
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return CodigosRetorno.ERROR_ADOPCION_NO_EXISTENTE;
		boolean finalizarOK = adopcion.finalizarSeguimiento();
		if (!finalizarOK)
			return CodigosRetorno.ERROR_SEGUIMIENTO_FINALIZADO_PREVIAMENTE;
		return CodigosRetorno.SEGUIMIENTO_FINALIZADO;
	}

}

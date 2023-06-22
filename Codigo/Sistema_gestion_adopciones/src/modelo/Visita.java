package modelo;

import java.util.Date;

import modelo.dto.EncuestaDto;
import repositorios.VisitaRepository;

public class Visita {

	public Visita(Date fecha, EncuestaDto encuesta, Adopcion adopcion) {
		this.fecha = fecha;
		this.encuesta = new Encuesta();
		this.encuesta.completar(encuesta);
		this.adopcion = adopcion;
	}
	private Date fecha;
	private Encuesta encuesta;
	private Adopcion adopcion;
	public static void registrar(Visita v, Animal animal) {
		animal.agregarVisitaAFicha(v);
		VisitaRepository.guardar(v);
	}
	
	
	
}

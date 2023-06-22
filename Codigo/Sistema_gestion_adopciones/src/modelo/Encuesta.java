package modelo;

import modelo.dto.EncuestaDto;
import modelo.enumeraciones.Calificacion;

public class Encuesta {

	private EncuestaDto encuestaData;
	
	public void completar(EncuestaDto encuestaDto) {
		this.encuestaData = encuestaDto;
	}
	
	public Calificacion getAmbiente() {
		return this.encuestaData.getAmbiente();
	}
	
	public Calificacion getLimpiezaLugar() {
		return this.encuestaData.getLimpiezaLugar();
	}
	
	public Calificacion getEstadoAnimal() {
		return this.encuestaData.getEstadoAnimal();
	}
}

package modelo;

import modelo.dto.EncuestaDto;

public class Encuesta {

	private EncuestaDto encuestaData;
	
	public void completar(EncuestaDto encuestaDto) {
		this.encuestaData = encuestaDto;
	}
}

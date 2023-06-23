package modelo.notificador;

import modelo.dto.AnimalDto;

public class Notificacion {
	private String mensaje;
	
	public Notificacion(AnimalDto animal, String fecha) {
		this.mensaje = "!!!EL dia " + fecha + ", tiene agendada la visita de ID: " + animal.getId() + "!!!";
		
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	 
}

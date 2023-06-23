package modelo.notificador;

import modelo.dto.AnimalDto;

public class Notificacion {
	private String mensaje;
	
	public Notificacion(int id, int fecha) {
		this.mensaje = "!!!EL dia " + fecha + ", tiene agendada la visita de la adopcion: " +id + "!!!";
		
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	 
}

package modelo;

import modelo.dto.AccionDto;
import modelo.enumeraciones.EstadoAccion;

public class Accion {

	private String nombre;
	private String comentario;
	private boolean estaCompletada = false;
	//private Veterinario veterinario;
	
	public Accion(String nombre) {
		this.nombre = nombre;
	}
	
	public void completar(String comentario) {
		this.estaCompletada = true;
		this.comentario = comentario;
	}
	
	public AccionDto toDto() {
		return new AccionDto(this.nombre, this.comentario, this.estaCompletada);
	}
	
	
}

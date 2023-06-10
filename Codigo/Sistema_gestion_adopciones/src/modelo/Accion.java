package modelo;

import modelo.enumeraciones.EstadoAccion;

public class Accion {

	private String nombre;
	private EstadoAccion estado;
	private String comentario;
	//private Veterinario veterinario;
	
	public Accion(String nombre) {
		this.nombre = nombre;
	}
	
}

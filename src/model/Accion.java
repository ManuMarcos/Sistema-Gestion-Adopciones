package model;

public class Accion {
	private String nombre;
	private EstadoAccion estado;
	private String comentario;
	private Veterinario veterinario;
	
	public Accion(String nombre, Veterinario veterinario) {
		this.nombre = nombre;
		this.estado = EstadoAccion.PENDIENTE;
		this.comentario = "";
		this.veterinario = veterinario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoAccion getEstado() {
		return estado;
	}

	public void setEstado(EstadoAccion estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

}

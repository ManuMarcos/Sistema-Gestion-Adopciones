package modelo.dto;

public class AccionDto {

	private String nombre;
	private String comentario;
	private boolean estaCompletada;
	
	public AccionDto(String nombre, String comentario, boolean estaCompletada) {
		this.nombre = nombre;
		this.comentario = comentario;
		this.estaCompletada = estaCompletada;
	}
}

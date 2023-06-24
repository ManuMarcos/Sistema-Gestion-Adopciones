package modelo;

import java.util.Date;

public class SeguimientoTratamiento{
	
	private Date fecha;
	private Tratamiento tratamiento;
	private Usuario veterinario;
	private String comentario;
	
	public SeguimientoTratamiento(Date fecha, Tratamiento tratamiento, Usuario veterinario, String comentario) {
		this.fecha = fecha;
		this.tratamiento = tratamiento;
		this.veterinario = veterinario;
		this.comentario = comentario;
	}	
}

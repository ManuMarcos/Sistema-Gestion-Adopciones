package modelo;

import java.util.Date;

public class SeguimientoTratamiento implements ITipoAlarma{
	
	private Date fecha;
	private Tratamiento tratamiento;
	private Veterinario veterinario;
	private String comentario;
	
	public SeguimientoTratamiento(Date fecha, Tratamiento tratamiento, Veterinario veterinario, String comentario) {
		this.fecha = fecha;
		this.tratamiento = tratamiento;
		this.veterinario = veterinario;
		this.comentario = comentario;
	}	
	
	
	
}

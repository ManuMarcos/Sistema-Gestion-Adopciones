package modelo;

import java.util.Date;
import java.util.List;

public class Tratamiento extends TipoAlarma {

	private Date fechaInicio;
	private Date fechaFin;
	private String nombre;
	private String descripcion;
	private boolean finalizado;
	
	public void finalizar() {
		this.finalizado = true;
	}

	public Tratamiento() {
	}
	
	public Tratamiento(Date fechaInicio, Date fechaFin, String nombre, String descripcion, List<Accion> acciones) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.finalizado = false;
	}


	@Override
	public Alarma crearAlarma(int periodicidad) {
		// TODO Auto-generated method stub
		return new Alarma(this, periodicidad);
	}

	
	
	
}

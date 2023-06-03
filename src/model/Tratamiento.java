package model;

import java.util.Date;
import java.util.List;

public class Tratamiento implements ITipoAlarma {
	private Date fechaInicio;
	private Date fechaFin;
	private String nombre;
	private String descripcion;
	private List<Accion> acciones;
	private boolean finalizado;
	
	public void finalizar() {
		this.finalizado = true;
	}
	
	public Tratamiento(Date fechaInicio, Date fechaFin, String nombre, String descripcion, List<Accion> acciones) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.acciones = acciones;
		this.finalizado = false;
	}

	@Override
	public void crearAlarma() {
		// TODO Auto-generated method stub		
	}
}

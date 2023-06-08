package modelo;

import java.util.Date;
import java.util.List;

public class Tratamiento implements ITipoAlarma{

	private Date fechaInicio;
	private Date fechaFin;
	private String nombre;
	private String descripcion;
	private List<Accion> acciones;
	private boolean finalizado = false;
	
	public void finalizar() {
		this.finalizado = true;
	}

	@Override
	public void crearAlarma() {
		// TODO Auto-generated method stub
		
	}
}

package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAlarma {

	private List<Accion> acciones;
	
	public TipoAlarma() {
		acciones = new ArrayList<Accion>();
	}
	
	public abstract Alarma crearAlarma(int periodicidad);
	
	public void agregarAccion(String nombre) {
		this.acciones.add(new Accion(nombre));
	}
	
	public List<Accion> getAcciones(){
		return this.acciones;
	}

}
package modelo;

import java.util.Date;
import java.util.List;

import modelo.enumeraciones.TipoDeAlarma;

public class Alarma {

	private ITipoAlarma tipoAlarma;
	private int periodicidad;
	private List<Accion> acciones;
	
	public Alarma(TipoDeAlarma tipoDeAlarma, int periodicidad) throws Exception {
		this.tipoAlarma = TipoAlarmaFactory.crearTipoAlarma(tipoDeAlarma);
		this.periodicidad = periodicidad;
	}
	
	public void agregarAccion(Accion accion) {
		this.acciones.add(accion);
	}
	
	public void atender() {
		
	}
	
	public void enviar(String notificacion) {
		
	}
}

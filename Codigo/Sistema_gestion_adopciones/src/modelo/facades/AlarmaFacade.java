package modelo.facades;

import modelo.Accion;
import modelo.Alarma;
import modelo.enumeraciones.TipoDeAlarma;

public class AlarmaFacade {

	public Alarma crearAlarmaDeControl(String[] acciones, int periodicidad) throws Exception {
		if (acciones.length == 0) {
			throw new Exception("ERROR. Se debe agregar como minimo una accion");
		}
		Alarma alarma = new Alarma(TipoDeAlarma.CONTROL, periodicidad);
		for (String accion : acciones) {
			alarma.agregarAccion(new Accion(accion));
		}
		return alarma;
	}
	
	
}

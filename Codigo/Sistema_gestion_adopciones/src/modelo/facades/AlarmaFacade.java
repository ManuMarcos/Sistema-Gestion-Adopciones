package modelo.facades;

import java.util.List;

import modelo.Alarma;
import modelo.Animal;
import modelo.FactoryTipoAlarma;
import modelo.TipoAlarma;
import modelo.enumeraciones.TipoDeAlarma;

public class AlarmaFacade {

	public void crearAlarma(Animal animal, TipoDeAlarma tipo, int periodicidad, List<String> acciones) {
		Alarma alarma =  new Alarma(FactoryTipoAlarma.crearTipoDeAlarma(tipo), periodicidad);
		for (String nombreAccion : acciones) {
			alarma.agregarAccion(nombreAccion);
		}
		animal.agregarAlarma(alarma);
	}
	
	public List<Alarma> listarAlarmas(Animal animal){
		return animal.obtenerAlarmas();
	}
	
}

package modelo.notificador;

import modelo.enumeraciones.MedioNotificacion;

public class FacadeNotificador {

	public void notificar(Notificacion notificacion, MedioNotificacion medio) {
		IEstrategiaNotificacion estrategia= FactoryNotificacion.crearEstrategia(medio);
		estrategia.enviar(notificacion);
	}
	
}

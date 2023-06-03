package model;

import java.time.Duration;

public class Alarma {
	private ITipoAlarma tipo;
	private Duration periodicidad;
	private IAdapterNotificacionPush notificador;
	
	public Alarma(ITipoAlarma tipo, Duration periodicidad, IAdapterNotificacionPush notificador) {
		super();
		this.tipo = tipo;
		this.periodicidad = periodicidad;
		this.notificador = notificador;
	}

	public void atender() {
		// TODO
	}
	
	public void enviar(String notificacion) {
		notificador.enviar(notificacion);
	}
}

package model;

import java.time.LocalDateTime;

public class Adopcion {
	private Visitador visitador;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private NotificadorStrategy notificador;
	private boolean continuarConVisitas;
	
	public void finalizarSeguimiento() {
		continuarConVisitas = false;
	}
	
	public void cambiarNotificacion(NotificadorStrategy notificador) {
		this.notificador = notificador;
	}
}

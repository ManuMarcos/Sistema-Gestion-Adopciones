package modelo;

import java.time.LocalDateTime;

public class Adopcion {
	private Cliente cliente;
	private Visitador visitador;
	private Animal animal;
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

	public Adopcion(Cliente cliente, Visitador visitador, Animal animal) {
		this.cliente = cliente;
		this.visitador = visitador;
		this.animal = animal;
		inicio = LocalDateTime.now();
		continuarConVisitas = true;
	}
}

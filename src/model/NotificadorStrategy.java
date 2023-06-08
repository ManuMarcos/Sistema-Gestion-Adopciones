package model;

public abstract class NotificadorStrategy {
	private int diasPrevios;
	public abstract void enviar(String notificacion);
}

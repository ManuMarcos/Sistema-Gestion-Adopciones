package model;

public class NotificadorEmail extends NotificadorStrategy {

	@Override
	public void enviar(String notificacion) {
		// TODO: adapter
		System.out.println("WHATSAPP NOTIF > Sale un email");
	}

}

package model;

public class NotificadorSMS extends NotificadorStrategy {

	@Override
	public void enviar(String notificacion) {
		// TODO: adapter
		System.out.println("SMS NOTIF > Sale un SMS");
	}

}

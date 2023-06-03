package model;

public class Firebase implements IAdapterNotificacionPush {

	@Override
	public void enviar(String notificacion) {
		System.out.printf("FIREBASE > SEND NOTIFICATION : [%s]", notificacion);
	}

}

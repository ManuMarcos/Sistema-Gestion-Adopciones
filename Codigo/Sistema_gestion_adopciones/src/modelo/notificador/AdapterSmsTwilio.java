package modelo.notificador;

public class AdapterSmsTwilio implements IAdapterNotificacionSms {

	@Override
	public void enviarSms(Notificacion notificacion) {
		System.out.println(notificacion.getMensaje());
		System.out.println("Enviado via SMS");
		
	}

}

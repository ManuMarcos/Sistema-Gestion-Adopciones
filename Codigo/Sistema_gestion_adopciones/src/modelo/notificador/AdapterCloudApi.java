package modelo.notificador;

public class AdapterCloudApi implements IAdapterNotificacionWhatsApp {

	@Override
	public void enviarWhatsApp(Notificacion notificacion) {
		System.out.println(notificacion.getMensaje());
		System.out.println("Enviado via Whatsapp");
		
	}

}

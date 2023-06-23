package modelo.notificador;

public class AdapterJavaMail implements IAdapterNotificacionEmail {

	@Override
	public void enviarMail(Notificacion notificacion) {
		System.out.println(notificacion.getMensaje());
		System.out.println("Enviado via JavaMail");
		
	}

}

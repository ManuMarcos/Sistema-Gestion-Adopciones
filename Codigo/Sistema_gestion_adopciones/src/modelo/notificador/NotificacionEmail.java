package modelo.notificador;

public class NotificacionEmail implements IEstrategiaNotificacion {

	private IAdapterNotificacionEmail adapter;
	

	public NotificacionEmail(IAdapterNotificacionEmail adapter) {
		this.adapter = adapter;
	}

	@Override
	public void enviar(Notificacion notificacion) {
		this.adapter.enviarMail(notificacion);

	}

}

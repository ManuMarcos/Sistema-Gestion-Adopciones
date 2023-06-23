package modelo.notificador;

public class NotificacionSms implements IEstrategiaNotificacion{

	private IAdapterNotificacionSms adapter;
	
	public NotificacionSms(IAdapterNotificacionSms adapter) {
		this.adapter = adapter;
	}

	@Override
	public void enviar(Notificacion notificacion) {
		this.adapter.enviarSms(notificacion);
		
	}

}

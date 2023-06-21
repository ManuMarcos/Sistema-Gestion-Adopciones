package modelo.notificador;

import modelo.enumeraciones.MedioNotificacion;

public class FactoryNotificacion {

	public static IEstrategiaNotificacion crearEstrategia(MedioNotificacion medio) {
		IEstrategiaNotificacion estrategia = null;
		switch(medio) {
			case SMS:
				estrategia = new NotificacionSms(new AdapterSmsTwilio());
				break;
			case WHATSAPP:
				estrategia = new NotificacionWhatsApp(new AdapterCloudApi());
				break;
			case EMAIL:
				estrategia = new NotificacionEmail(new AdapterJavaMail());
				break;
			case PUSH:
				estrategia = new NotificacionPush();
				break;
		}
		return estrategia;
	}
}

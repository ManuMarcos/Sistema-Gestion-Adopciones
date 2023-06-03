package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FirebaseTest {

	@Test
	void enviarNotificacion() {
		IAdapterNotificacionPush notif = new Firebase();
		notif.enviar("una notificacion");
		assertNotNull(notif);
	}

}

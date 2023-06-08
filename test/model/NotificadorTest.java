package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotificadorTest {

	@Test
	void SMStest() {
		NotificadorStrategy notif = new NotificadorSMS();
		notif.enviar("sms prueba");
	}

	@Test
	void Emailtest() {
		NotificadorStrategy notif = new NotificadorEmail();
		notif.enviar("email prueba");
	}

	@Test
	void WhatsApptest() {
		NotificadorStrategy notif = new NotificadorWhatsApp();
		notif.enviar("whatsapp prueba");
	}

}

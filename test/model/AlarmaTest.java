package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class AlarmaTest {

	@Test
	void test() {
		Alarma alarma = new Alarma(new Control(), Duration.ofMinutes(12), new Firebase());
		alarma.enviar("notificacion alarma test");
		assertNotNull(alarma);
	}

}

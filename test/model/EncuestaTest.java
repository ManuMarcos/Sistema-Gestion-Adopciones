package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EncuestaTest {

	@Test
	void test() {
		Encuesta e = new Encuesta();
		e.completarEncuesta(new EncuestaDto(Calificacion.REGULAR, Calificacion.REGULAR, Calificacion.REGULAR));
	}

}

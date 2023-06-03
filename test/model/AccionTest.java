package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccionTest {

	@Test
	void constructor() {
		var x = new Accion("Dar medicamentos", 808);
		assertNotNull(x);
	}
	
	// TODO: getters y setters?

}

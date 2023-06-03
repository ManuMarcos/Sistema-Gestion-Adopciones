package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccionTest {

	@Test
	void constructor() {
		var x = new Accion("Dar medicamentos", new Veterinario());
		assertNotNull(x);
	}
	
	// TODO: getters y setters?

}

package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

// meto aca las derivadas.
public class IFormatoStrategyTest {
	@Test
	void formatoExcel() {
		FichaMedica fm = new FichaMedica();
		IFormatoStrategy strategy = new FormatoExcel();
		strategy.exportar(fm);
	}

	@Test
	void formatoPFD() {
		FichaMedica fm = new FichaMedica();
		IFormatoStrategy strategy = new FormatoPDF();
		strategy.exportar(fm);
	}
}

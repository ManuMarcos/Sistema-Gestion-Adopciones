package modelo;

import modelo.enumeraciones.TipoDeAlarma;

public class FactoryTipoAlarma {

	public static TipoAlarma crearTipoDeAlarma(TipoDeAlarma tipo) {
		TipoAlarma tipoAlarma = null;
		switch (tipo) {
			case CONTROL:
				tipoAlarma = new Control();
				break;
			case TRATAMIENTO:
				tipoAlarma = new Tratamiento();
				break;
		}
		return tipoAlarma;
	}
	
}

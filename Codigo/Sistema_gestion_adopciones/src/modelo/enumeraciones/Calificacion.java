package modelo.enumeraciones;

public enum Calificacion {
	MALO,
	REGULAR,
	BUENO;
	public static Calificacion toCalificacion(int valor) {
		if(valor == 1)
			return Calificacion.MALO;
		if(valor == 2)
			return Calificacion.REGULAR;
		if(valor == 3)
			return Calificacion.BUENO;
		throw new RuntimeException("toCalificacion: valor invalido [" + valor + "]");
	}
}

package modelo;

public class TipoAlarmaFactory {

	public static ITipoAlarma crearTipoAlarma(TipoDeAlarma tipo) throws Exception {
		switch (tipo){
			case CONTROL:
				return (ITipoAlarma) new Control();	
			case TRATAMIENTO:
				return new Tratamiento();
			default:
				throw new Exception("No existe la clase");
		}
	}
}

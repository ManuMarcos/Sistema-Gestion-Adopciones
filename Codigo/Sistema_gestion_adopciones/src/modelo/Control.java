package modelo;



public class Control extends TipoAlarma{
	
	public Control() {
		super();
	}

	@Override
	public Alarma crearAlarma(int periodicidad) {
		// TODO Auto-generated method stub
		return new Alarma(this, periodicidad);
	}

	
}

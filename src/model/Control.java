package model;

import java.util.ArrayList;
import java.util.List;

public class Control implements ITipoAlarma {
	private List<Accion> acciones;

	public Control() {
		acciones = new ArrayList<Accion>();
	}

	
	public Control(List<Accion> acciones) {
		super();
		this.acciones = acciones;
	}

	@Override
	public void crearAlarma() {
		// TODO Auto-generated method stub
	}
}

package vistas;

import controladores.VisitasController;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.ICliView;

public class VisitasView implements ICliView {

	public void setControlador(VisitasController controladorVisitas) {
		// TODO Auto-generated method stub

	}

	@Override
	public CliViewNames procesar() {
		// TODO Auto-generated method stub
		return CliViewNames.BACK;
	}

}

package model;

import java.util.List;

public class FichaMedica {
	private IFormatoStrategy formato;
	private Animal animal;
	private List<Visita> visitas;
	private List<Control> controles;
	private List<SeguimientoTratamiento> tratamientos;
	
	public void exportar() {
		formato.exportar(this);
	}
	
	public void cambiarFormatoExportacion(IFormatoStrategy formato) {
		this.formato = formato;
	}
}

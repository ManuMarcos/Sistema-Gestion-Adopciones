package modelo;

import java.util.List;

public class FichaMedica {

	private Animal animal;
	private List<Control> controles;
	private List<SeguimientoTratamiento> tratamientos;
	private List<Visita> visitas;
	private IFormatoStrategy formato;
	
	public FichaMedica(Animal animal) {
		this.animal = animal;
		this.controles = controles;
		this.tratamientos = tratamientos;
		this.visitas = visitas;
		
		//Por default pongo que se pueda exportar solo en PDF
		this.formato = new FormatoPdf();
	}
}

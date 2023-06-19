package modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.dto.FichaMedicaDto;
import modelo.exportacion.IExportable;
import modelo.utils.Utils;

public class FichaMedica implements IExportable{

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
		
	}
	
	public FichaMedicaDto toDto() {
		return new FichaMedicaDto(this.animal.getTipo().toString(),
				this.animal.getEspecie(), this.animal.getPeso(), this.animal.getAltura()
				, this.animal.getFecha_nac(), this.animal.getEstado().toString());
	}
	
	
	
	public void exportar() {
		formato.exportar(this);
	}
	
	public void cambiarFormatoExportacion(IFormatoStrategy formato) {
		this.formato = formato;
	}

	
	
	//TO DO: La ficha medica deberia saber devolver sus datos en un Map
	@Override
	public Map<String, String> datos() {
		// TODO Auto-generated method stub
		Map<String, String> datos = new HashMap<String, String>();
		String patronFecha = "dd/MM/yyyy";
		SimpleDateFormat formatoFecha = new SimpleDateFormat(patronFecha);
		
		datos.put("Tipo", this.animal.getTipo().toString().toLowerCase());
		datos.put("Especie", this.animal.getEspecie());
		datos.put("Altura", Integer.toString(this.animal.getAltura()) + " cm");
		datos.put("Peso", Integer.toString(this.animal.getPeso()) + " g");
		datos.put("Fecha Nacimiento" , formatoFecha.format(this.animal.getFecha_nac()));
		datos.put("Condicion medica", this.animal.getEstado().toString().replace("_", " ").toLowerCase());
		return datos;
	}
}

package modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
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

	@Override
	public Map<String,List<String>> datos() {
		// TODO Auto-generated method stub
		Map<String, List<String>> datos = new HashMap<String, List<String>>();
		String patronFecha = "dd/MM/yyyy";
		SimpleDateFormat formatoFecha = new SimpleDateFormat(patronFecha);
		List<String> columnas = Arrays.asList("Tipo", "Especie", "Altura", "Peso", 
				"Fecha Nacimiento", "Condicion Medica");
		List<String> celdas = Arrays.asList(
				/*Tipo*/ this.animal.getTipo().toString().toLowerCase(),
				/*Especie*/ this.animal.getEspecie(),
				/*Altura*/ Integer.toString(this.animal.getAltura()) + " cm",
				/*Peso*/ Integer.toString(this.animal.getPeso()) + " g",
				/*Fecha Nacimiento*/ formatoFecha.format(this.animal.getFecha_nac()),
				/*Estado*/ this.animal.getEstado().toString().replace("_", " ").toLowerCase());
		
		datos.put("0", columnas);
		datos.put("1", celdas);
		
		return datos;
	}
	
}

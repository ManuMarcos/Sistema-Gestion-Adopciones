package modelo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.dto.FichaMedicaDto;
import modelo.exportacion.FormatoExportacion;
import modelo.exportacion.IExportable;


public class FichaMedica implements IExportable{

	private Animal animal;
	private List<Alarma> alarmas;
	private List<Visita> visitas;
	
	public FichaMedica(Animal animal) {
		this.animal = animal;
		this.alarmas = new ArrayList<Alarma>();
		this.visitas = new ArrayList<Visita>();
	}
	
//	public FichaMedicaDto toDto() {
//		return new FichaMedicaDto(this.animal.buscarAnimal(animal.getId()).toDto());
//	}
	
	public void agregarAlarma(Alarma alarma) {
		this.alarmas.add(alarma);
	}
	
	public List<Alarma> getAlarmas(){
		return this.alarmas;
	}

	@Override
	public Map<String,List<String>> datos() {
		// TODO Auto-generated method stub
		Map<String, List<String>> datos = new HashMap<String, List<String>>();
		List<String> columnas = Arrays.asList("Tipo", "Especie", "Altura", "Peso", 
				"Fecha Nacimiento", "Condicion Medica");
		List<String> celdas = Arrays.asList(
				/*Tipo*/ this.animal.getTipo().toString().toLowerCase(),
				/*Especie*/ this.animal.getEspecie(),
				/*Altura*/ Integer.toString(this.animal.getAltura()) + " cm",
				/*Peso*/ Integer.toString(this.animal.getPeso()) + " g",
				/*Fecha Nacimiento*/ this.animal.getFecha_nac().toString(),
				/*Estado*/ this.animal.getEstado().toString().replace("_", " ").toLowerCase());
		
		datos.put("0", columnas);
		datos.put("1", celdas);
		
		return datos;
	}

	public boolean agregarVisita(Visita v) {
		this.visitas.add(v);
		return true;
	}

	public List<Visita> getVisitas() {
		return this.visitas;
	}
	
}

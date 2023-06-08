package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.dto.AnimalDto;

public class Refugio {

	private List<Animal> animales;
	private static Refugio instance;
	
	
	private Refugio() {
		this.animales = new ArrayList<Animal>();
	}
	
	public static Refugio getInstance() {
		if (instance == null) {
			instance = new Refugio();
			return instance;
		}
		return instance;
	}
	
	public void guardarAnimal(AnimalDto animalDto) {
		Animal animal = new Animal(animalDto.getAltura(), animalDto.getPeso(), animalDto.getFecha_nac(), animalDto.getEspecie()
				,animalDto.getEstado(), animalDto.getTipo());
		this.animales.add(animal);
	}
	
	//El listado deberia ser de AnimalDto
	public List<Animal> obtenerListadoAnimales(){
		return this.animales;
	}
	
}

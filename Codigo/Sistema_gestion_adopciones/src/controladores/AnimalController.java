package controladores;

import java.util.List;

import modelo.Animal;
import modelo.Refugio;
import modelo.dto.AnimalDto;

public class AnimalController {

	
	public void cargarAnimal(AnimalDto animalDto) {
		Refugio.getInstance().guardarAnimal(animalDto);
	}
	
	public List<Animal> listadoAnimales(){
		return Refugio.getInstance().obtenerListadoAnimales();
	}
}

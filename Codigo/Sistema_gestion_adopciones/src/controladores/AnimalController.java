package controladores;

import java.util.List;

import modelo.Refugio;
import modelo.dto.AnimalDto;
import vistas.AnimalCli;

public class AnimalController {

	private AnimalCli vista;

	public AnimalController() {
		this.vista = new AnimalCli();
		this.vista.setController(this);
		this.vista.mostarMenu();
	}

	public void cargarAnimal(AnimalDto animalDto) {
		Refugio.getInstance().guardarAnimal(animalDto);
	}

	public List<AnimalDto> listadoAnimales() {
		return Refugio.getInstance().obtenerListadoAnimales();
	}

}

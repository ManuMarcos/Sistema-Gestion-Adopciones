package controladores;

import java.util.Date;
import java.util.List;

import modelo.Animal;
import modelo.Refugio;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
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

	// TODO: agrego esto porque necesito poder conseguir un animal por id (o alguna
	// PK) para avanzar. despues implementarlo bien...
	public AnimalDto getAnimalHardCodeado(int id) {
		if (id == 77)
			return new AnimalDto(id, 60, 60, new Date(), "Gato", EstadoAnimal.SALUDABLE, TipoAnimal.DOMESTICO);
		else
			return null;
	}

}

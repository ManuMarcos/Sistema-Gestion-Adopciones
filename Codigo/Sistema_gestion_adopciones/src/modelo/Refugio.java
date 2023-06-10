package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.dto.AnimalDto;
import modelo.dto.ClienteDto;

public class Refugio {

	//Singleton
	private List<Animal> animales;
	private List<Cliente> clientes;
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
	
	public List<AnimalDto> obtenerListadoAnimales(){
		List<AnimalDto> animalesDto = new ArrayList<AnimalDto>();
		for (Animal animal : this.animales) {
			animalesDto.add(animal.toDto());
		}
		return animalesDto;
	}
	
	public void guardarCliente(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		this.clientes.add(cliente);
	}
	
}

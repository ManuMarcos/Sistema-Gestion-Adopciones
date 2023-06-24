package modelo.dao;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Animal;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

public class InMemoryAnimalDao implements AnimalDao {

	private static List<Animal> animales = new ArrayList<Animal>();

	static {
		precargarAnimal();
	}

	private static void precargarAnimal() {
		Animal animalPrecargado = new Animal(1, 60, 60, LocalDate.now(), "Gato", EstadoAnimal.SALUDABLE,
				TipoAnimal.DOMESTICO);
		animales.add(animalPrecargado);
	}

	@Override
	public List<Animal> getAll() {
		// TODO Auto-generated method stub
		return animales;
	}

	@Override
	public Animal getByNroIngreso(int nroIngreso) {
		// TODO Auto-generated method stub
		for (Animal animal : animales) {
			if (animal.getNroIngreso() == nroIngreso) {
				return animal;
			}
		}
		// System.out.println("Animal no encontrado");
		return null;
	}

	
	//El nro ingresado por el usuario debe ser mayor a 0
	@Override
	public void add(Animal animal) {
		// TODO Auto-generated method stub
		if(animal.getNroIngreso() > 0) {
			Animal animalBuscado = getByNroIngreso(animal.getNroIngreso());
			if(animalBuscado == null) {
				animales.add(animal);
			}
			else {
				update(animal);
			}
		}
		else {
			System.out.println("El nro de Ingreso debe ser mayor a 0");
		}
	}

	@Override
	public boolean update(Animal animal) {
		// TODO Auto-generated method stub
		Animal animalToUpdate = getByNroIngreso(animal.getNroIngreso());
		if (animalToUpdate != null) {
			animalToUpdate.setTipo(animal.getTipo());
			animalToUpdate.setEspecie(animal.getEspecie());
			animalToUpdate.setAltura(animal.getAltura());
			animalToUpdate.setPeso(animal.getPeso());
			animalToUpdate.setFecha_nac(animal.getFecha_nac());
			animalToUpdate.setEstado(animal.getEstado());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int nroIngreso) {
		// TODO Auto-generated method stub
		Animal animalToDelete = getByNroIngreso(nroIngreso);
		if (animalToDelete != null) {
			animales.remove(animalToDelete);
			return true;
		}
		return false;
	}

}

package modelo.dao;

import java.util.List;
import java.util.ArrayList;
import modelo.Animal;


public class InMemoryAnimalDao implements AnimalDao{

	private static List<Animal> animales = new ArrayList<Animal>();
	private static int generador = 1;
	

	@Override
	public List<Animal> getAll() {
		// TODO Auto-generated method stub
		return animales;
	}

	@Override
	public Animal getById(int id) {
		// TODO Auto-generated method stub
		for (Animal animal : animales) {
			if (animal.getId() == id) {
				return animal;
			}
		}
		//System.out.println("Animal no encontrado");
		return null;
	}

	@Override
	public boolean add(Animal animal) {
		// TODO Auto-generated method stub
		Animal animalBuscado = getById(animal.getId());
		if (animalBuscado == null) {
			animal.setId(generador);
			generador++;
			animales.add(animal);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Animal animal) {
		// TODO Auto-generated method stub
		Animal animalToUpdate = getById(animal.getId());
		if (animalToUpdate != null) {
			animalToUpdate = animal;
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Animal animalToDelete = getById(id);
		if (animalToDelete != null) {
			animales.remove(animalToDelete);
			return true;
		}
		return false;
	}

	
	
}

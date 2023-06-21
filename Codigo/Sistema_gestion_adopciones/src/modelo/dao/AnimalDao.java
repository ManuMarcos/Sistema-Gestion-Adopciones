package modelo.dao;

import java.util.List;

import modelo.Animal;

public interface AnimalDao {

	List<Animal> getAll();
	
	Animal getById(int id);
	
	boolean add(Animal animal);
	
	boolean update(Animal animal);
	
	boolean delete(int id);
	
	
}

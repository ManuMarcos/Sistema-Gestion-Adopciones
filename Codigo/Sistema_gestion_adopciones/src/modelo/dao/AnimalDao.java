package modelo.dao;

import java.util.List;

import modelo.Animal;

public interface AnimalDao {

	List<Animal> getAll();
	
	Animal getByNroIngreso(int nroIngreso);
	
	void add(Animal animal);
	
	boolean update(Animal animal);
	
	boolean delete(int nroIngreso);
	
	
}

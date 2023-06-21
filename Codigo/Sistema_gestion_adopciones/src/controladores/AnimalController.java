package controladores;

import java.util.List;
import java.util.ArrayList;
import modelo.Animal;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import vistas.AnimalView;
import vistas.VentanaPrincipalAnimal;
import vistas.VentanaRegistroAnimal;

public class AnimalController {

	private VentanaPrincipalAnimal ventanaPrincipal;
	private VentanaRegistroAnimal ventanaRegistro;
	
	

	public AnimalController() {}
	
	public AnimalController(VentanaPrincipalAnimal vista) {
		this.ventanaPrincipal = vista;
	}

	public void setVentanaPrincipal(VentanaPrincipalAnimal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	public void setVentanaRegistro(VentanaRegistroAnimal ventanaRegistro) {
		this.ventanaRegistro = ventanaRegistro;
		
	}
	
	public void mostrarVentanaPrincipal() {
		this.ventanaPrincipal.setVisible(true);
	}
	
	public void mostrarVentanaRegistro() {
		this.ventanaRegistro.setVisible(true);
	}
	
	
	
	public void cargarAnimal(AnimalDto animalDto) {
		Animal animal = new Animal();
		animal.registrarAnimal(animalDto.toEntity());
	}
	
	public List<AnimalDto> listarAnimales(){
		Animal animal = new Animal();
		List<Animal> animales = animal.getAll();
		List<AnimalDto> animalesDto = new ArrayList<AnimalDto>();
		
		for(Animal animalI : animales) {
			animalesDto.add(animalI.toDto());
		}
		return animalesDto;
	}
	
	public List<String> getTipos(){
		List<String> tipos = new ArrayList<String>();
		for(TipoAnimal tipo : TipoAnimal.values()) {
			tipos.add(tipo.toString());
		}
		return tipos;
	}
	
	public List<String> getEstados(){
		List<String> estados = new ArrayList<String>();
		for(EstadoAnimal estado : EstadoAnimal.values()) {
			estados.add(estado.toString());
		}
		return estados;
	}
	

}

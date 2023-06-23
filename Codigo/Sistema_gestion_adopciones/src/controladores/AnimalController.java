package controladores;

import java.util.List;
import java.util.ArrayList;
import modelo.Animal;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import modelo.exportacion.FacadeExportador;
import modelo.exportacion.FormatoExportacion;
import vistas.AnimalView;
import vistas.VentanaPrincipalAnimal;
import vistas.VentanaDatosAnimal;

public class AnimalController {

	private VentanaPrincipalAnimal ventanaPrincipal;
	private VentanaDatosAnimal ventanaDatos;
	
	

	public AnimalController() {}
	
	public AnimalController(VentanaPrincipalAnimal vista) {
		this.ventanaPrincipal = vista;
	}

	public void setVentanaPrincipal(VentanaPrincipalAnimal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	public void setVentanaRegistro(VentanaDatosAnimal ventanaDatos) {
		this.ventanaDatos = ventanaDatos;
		
	}
	
	public void mostrarVentanaPrincipal() {
		this.ventanaPrincipal.setVisible(true);
	}
	
	public void mostrarVentanaDatos() {
		this.ventanaDatos.registrarAnimal();
		this.ventanaDatos.setVisible(true);
	}
	
	public void mostrarVentanaDatos(int id) {
		Animal animal = new Animal();
		this.ventanaDatos.mostrarDatos(animal.buscarAnimal(id).toDto());
		this.ventanaDatos.setVisible(true);
	}
	

	public void exportarFichaMedica(int id, FormatoExportacion formatoExportacion, String nombreArchivo) {
		Animal animal = new Animal();
		FacadeExportador exportador = new FacadeExportador();
		String rutaCompletaArchivo = exportador.exportar(animal.buscarAnimal(id).getFichaMedica(), formatoExportacion, nombreArchivo);
		this.ventanaDatos.mostrarMensajeInfo("Archivo exportado con exito, ruta: "  + rutaCompletaArchivo);
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


}

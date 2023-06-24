package controladores;

import java.util.List;
import java.util.ArrayList;
import modelo.Alarma;
import modelo.Animal;
import modelo.dto.AlarmaDto;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.TipoDeAlarma;
import modelo.facades.AlarmaFacade;
import vistas.AlarmaView;

public class AlarmaController {

	private AlarmaView vista;
	
	public AlarmaController(AlarmaView vista) {
		this.vista = vista;
	}
	
	
	public void crearAlarma(AnimalDto animalDto, TipoDeAlarma tipo, int periodicidad, List<String> acciones) {
		AlarmaFacade alarmaFacade = new AlarmaFacade();
		Animal animal = new Animal();
		alarmaFacade.crearAlarma(animal.buscarAnimal(animalDto.getNroIngreso()), tipo, periodicidad, acciones);
	}
	
	public List<AlarmaDto> listarAlarmas(AnimalDto animalDto){
		Animal animal = new Animal();
		AlarmaFacade alarmaFacade = new AlarmaFacade();
		List<AlarmaDto> alarmas = new ArrayList<AlarmaDto>();
		for(Alarma alarma : alarmaFacade.listarAlarmas(animal.buscarAnimal(animalDto.getNroIngreso()))){
			alarmas.add(alarma.toDto());
		}
		return alarmas;
	}
	
}

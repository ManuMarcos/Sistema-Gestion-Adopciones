package vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controladores.AlarmaController;
import controladores.AnimalController;
import modelo.Animal;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.TipoDeAlarma;
import vistas.AdopcionView.OptionNuevaAdopcion;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AlarmaView implements ICliView{

	private AlarmaController controlador;
	private AnimalController controladorAnimal;
	private static Scanner sc;
	private static final String[] nombresOpciones = { "Crear alarma", "Actualizar alarma", "Ver Alarmas", "Atras"};
	private List<ICliOption> opciones;
	
	public void setController(AlarmaController controlador) {
		this.controlador = controlador;
	}
	public void mostrarMenu(AnimalDto animal) {
		System.out.printf("1 - Crear alarma            %n");
		System.out.printf("2 - Actualizar alarma       %n");
		System.out.printf("3 - Ver Alarmas             %n");
		System.out.printf("4 - Atras             %n");
	}
	
	class OptionCrearAlarma implements ICliOption {
		String[] opcionesTipoAlarma = {"Control", "Tratamiento"};
		
		
		
		private TipoDeAlarma obtenerTipoAlarma(int tipo) {
			System.out.println("Tipo: "+TipoDeAlarma.values()[tipo-1]);
			return TipoDeAlarma.values()[tipo-1];
		}
		private int establecerPeriodicidad() {
			int periodicidad=0;
			System.out.println("Ingrese la periodicidad de la alarma");
			periodicidad=sc.nextInt();
			return periodicidad;
		}
		private List<String> establecerAcciones(){
			List<String> acciones = new ArrayList<String>();
			boolean fin = false;
			while(!fin){
				String inputAcciones = IngresoCli
						.solicitarStringNoNulo("Ingrese las acciones a realizar ('.' para terminar ingreso): ");
				if (inputAcciones.equals("."))
				{
					fin = true;
					break;
				}
				else {
					acciones.add(inputAcciones);
				}
			} 
			return acciones;
		}
		
		private AnimalDto obtenerAnimalDto(List<AnimalDto> animales) {
			AnimalDto animal = null;
				try {
					System.out.println("Ingrese el id del animal:");
					String opcion = sc.nextLine();
					int id = Integer.parseInt(opcion);
					for(AnimalDto currentAnimal: animales) {
						if(id==currentAnimal.getId()) {
							animal=currentAnimal;
						}
						else {
							System.out.println("Animal no encontrado, ingrese el id correcto");
							return obtenerAnimalDto(animales);
						}
					}
				}catch(Exception e){
					System.out.println("Error: Se ha ingresado un valor que no es valido");
					return obtenerAnimalDto(animales);
				}
			return animal;}

		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Crear Alarma");
			FormatoCli.printOpciones(opcionesTipoAlarma);
			int opcionTipoAlarma = IngresoCli.solicitarOpcion(opcionesTipoAlarma.length);
			TipoDeAlarma tipoAlarma= obtenerTipoAlarma(opcionTipoAlarma);
			int periodicidad = establecerPeriodicidad();
			List<String> acciones = establecerAcciones();
			AnimalDto animalDto = obtenerAnimalDto(controladorAnimal.listarAnimales());
			controlador.crearAlarma(animalDto,tipoAlarma,periodicidad,acciones);
			FormatoCli.printCabecera("Alarma creada exitosamente");
			return CliViewNames.MENU_PRINCIPAL;
		}
		
		
	}
	class OptionActualizarAlarma implements ICliOption {

		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Actualizar Alarma");
			
			return CliViewNames.MENU_PRINCIPAL;
		}
		
	}
	class OptionVerAlarmas implements ICliOption {

		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Lista de Alarmas");
			
			return CliViewNames.MENU_PRINCIPAL;
		}
		
	}

	public AlarmaView(AnimalController controladorAnimal) {
		this.controladorAnimal=controladorAnimal;
		sc=new Scanner(System.in);
		opciones = new ArrayList<>();
		opciones.add(new OptionCrearAlarma());
		opciones.add(new OptionActualizarAlarma());
		opciones.add(new OptionVerAlarmas());
		opciones.add(new ICliOption.OptionBack());
	}
	@Override
	public CliViewNames procesar() {
		// TODO Auto-generated method stub
		FormatoCli.printCabecera("Menú Alarmas");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}
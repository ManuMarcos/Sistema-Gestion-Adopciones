package vistas;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



import java.text.ParseException;
import java.text.SimpleDateFormat;

import controladores.AlarmaController;
import controladores.AnimalController;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.ICliView;

public class AnimalView {

	private AnimalController controlador;
	private Scanner entrada;
	
	public AnimalView() {
		this.entrada = new Scanner(System.in);
	}
	
	//temporal
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
	}
	
	public void mostrarMenu() {
		Scanner entrada = new Scanner(System.in);
		int opcionIngresada;
		
		System.out.printf("----------------------------------%n");
		System.out.printf("      Menu - Animales             %n");
		System.out.printf("----------------------------------%n");
		System.out.printf("1 - Ingresar nuevo animal         %n");
		System.out.printf("2 - Ver animales ingresados       %n");
		
		opcionIngresada = entrada.nextInt();
		entrada.nextLine();
		switch (opcionIngresada) {
			case 1:
				this.ingresarNuevoAnimal();
				break;
			case 2:
				this.mostrarAnimales();
				break;
			default:
				this.mostrarMenu();
		}
		
		
	}
	
	private void ingresarNuevoAnimal() {
		EstadoAnimal estado;
		int altura;
		int peso;
		
		
		String especie;
		AnimalDto animal;
		
		System.out.printf("----------------------------------%n");
		System.out.printf("      Ingesar nuevo animal        %n");
		System.out.printf("----------------------------------%n");
		System.out.printf("--Por favor completar los siguientes datos:--%n");
		
		//Tipo
		TipoAnimal tipoAnimal = obtenerTipoAnimal();
		
		//Altura
		altura = obtenerNumero("Altura(en cm):%n");
		
		//Peso
		peso = obtenerNumero("Peso(en g):%n");
		
		//Fecha Nacimiento
		entrada.nextLine();
		Date fechaNac = obtenerFechaNacimiento();
		
		//Especie
		System.out.printf("Especie:%n");
		especie = entrada.next();
		
		//Estado
		entrada.nextLine();
		estado = obtenerEstadoAnimal();
		
		//animal = new AnimalDto(altura, peso, fechaNac, especie, estado, tipoAnimal);
		
		//this.controlador.cargarAnimal(animal);
		
		System.out.println("Animal cargado correctamente!");
		this.mostrarMenu();
	}
	
	private int obtenerNumero(String mensaje) {
		System.out.printf(mensaje);
		String numeroS = entrada.next();
		try {
			int numero = Integer.parseInt(numeroS);
			return numero;
		}
		catch(Exception e) {
			System.out.println("Error: Se ha ingresado un valor que no es valido");
			return obtenerNumero(mensaje);
		}
	}
	

	
	private TipoAnimal obtenerTipoAnimal() {
		try {
			System.out.printf("Tipo de Animal: DOMESTICO o SALVAJE%n");
			String tipoAnimal = entrada.nextLine().toUpperCase();
			return TipoAnimal.valueOf(tipoAnimal);
		}
		catch(Exception ex) {
			System.out.println("Error: Se ha ingresado un valor que no es valido");
			return obtenerTipoAnimal();
		}
	}
	
	private Date obtenerFechaNacimiento() {
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
		try {
			System.out.printf("Fecha de nacimiento (dd/mm/yyyy):%n");
			Date fechaNac = formateadorFecha.parse(entrada.next());
			return fechaNac;
		}
		catch (ParseException e){
			System.out.println("Error: !Fecha Invalida!!");
			return obtenerFechaNacimiento();
		}
	}
	
	private EstadoAnimal obtenerEstadoAnimal() {
		try {
			System.out.printf("Estado: SALUDABLE o NECESITA ATENCION%n");
			String estadoAnimal = entrada.nextLine().toUpperCase();
			if (estadoAnimal.equals("NECESITA ATENCION")) {
				estadoAnimal = "EN_TRATAMIENTO";
			}
			return EstadoAnimal.valueOf(estadoAnimal);
		}
		catch(Exception e) {
			System.out.println("Error: Se ha ingresado un valor que no es valido");
			return obtenerEstadoAnimal();
		}
	}
	
	
	
	private void mostrarAnimales() {
		imprimirLinea();
		System.out.printf("| %-5s | %-10s | %-8s | %-20s | %-30s | %-8s | %-8s |%n", 
				"n°","Tipo", "Especie", "Estado", "Fecha Nac", "Altura", "Peso");
		imprimirLinea();
		List<AnimalDto> animales = this.controlador.listarAnimales();
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
	
		
		int i = 1;
		for (AnimalDto animal : animales) {
			System.out.printf("| %-5s | %-10s | %-8s | %-20s | %-30s | %-8s | %-8s |%n", 
					i,animal.getTipo(), animal.getEspecie(), animal.getEstado(), formateadorFecha.format(animal.getFecha_nac()), 
					animal.getAltura(), animal.getPeso());
			i++;
		}
		
		imprimirLinea();
		
		AnimalDto animalSeleccionado = obtenerAnimalSeleccionado(animales);
		
		if (animalSeleccionado != null) {
			mostrarMenuAnimal(animalSeleccionado);
		}
		else {
			mostrarMenu();
		}
	}
	

	private AnimalDto obtenerAnimalSeleccionado(List<AnimalDto> animales) {
		System.out.println("Ingrese el n° del animal (. para volver atras):");
		String opcion = entrada.nextLine();
		AnimalDto animal = null;
		if(!opcion.equals(".")) {
			try {
				int numero = Integer.parseInt(opcion);
				if ((numero >= 1) && (numero <= animales.size())) {
					animal = animales.get(numero - 1);
				}
				else {
					System.out.println("ERROR, la opcion ingresada no existe");
					obtenerAnimalSeleccionado(animales);
				}}
			catch(Exception e){
				System.out.println("Error: Se ha ingresado un valor que no es valido");
				obtenerAnimalSeleccionado(animales);
			}
		}
		return animal;
	}
	
	private void imprimirLinea() {
		int longitud = 111;
		for (int i = 0 ; i<longitud ; i++) {
			System.out.printf("-");
		}
		System.out.println();
	}
	
	private void mostrarMenuAnimal(AnimalDto animalDto) {
		int opcionIngresada;
		
		System.out.println();
		System.out.println();
		imprimirLinea();
		System.out.printf("| %-10s | %-8s | %-20s | %-30s | %-8s | %-8s |%n", 
				"Tipo", "Especie", "Estado", "Fecha Nac", "Altura", "Peso");
		System.out.printf("| %-10s | %-8s | %-20s | %-30s | %-8s | %-8s |%n", 
				animalDto.getTipo(), animalDto.getEspecie(), animalDto.getEstado(), animalDto.getFecha_nac(), animalDto.getAltura(), 
				animalDto.getPeso());
		imprimirLinea();
		System.out.printf("1 - Exportar ficha medica         %n");
		System.out.printf("2 - Ver historia clinica          %n");
		
		opcionIngresada = entrada.nextInt();
		switch (opcionIngresada) {
			case 1:
				System.out.println("MOSTRAR OPCIONES DE EXPORTACION FICHA MEDICA (PDF O EXCEL)");
				break;
			case 2:
				System.out.println("MOSTRAR HISTORIA CLINICA (EVOLUCION MEDICA, CHEQUEOS Y VISITAS)");
				break;
			case 3:
				AlarmaView alarmaView = new AlarmaView();
				AlarmaController alarmaController = new AlarmaController(alarmaView);
				alarmaView.setController(alarmaController);
				
			default:
				this.mostrarMenu();
		}
		
	}

	
	
	
	
	
	
}

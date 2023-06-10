package vistas;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import controladores.AnimalController;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

public class AnimalCli {

	private AnimalController controlador;
	private static Scanner sc;
	
	public AnimalCli() {
		sc = new Scanner(System.in);
	}
	
	//temporal
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
	}
	
	public void mostarMenu() {
		
		int opcionIngresada;
		
		System.out.printf("----------------------------------%n");
		System.out.printf("      Menu - Animales             %n");
		System.out.printf("----------------------------------%n");
		System.out.printf("1 - Ingresar nuevo animal         %n");
		System.out.printf("2 - Ver animales ingresados       %n");
		
		opcionIngresada = sc.nextInt();
		switch (opcionIngresada) {
			case 1:
				this.ingresarNuevoAnimal();
				break;
			case 2:
				this.mostrarAnimales();
				break;
			default:
				this.mostarMenu();
		}
		
		
	}
	
	private void ingresarNuevoAnimal() {
		TipoAnimal tipoAnimal = null;
		EstadoAnimal estado = null;
		int altura;
		int peso;
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaNac = null;
		String especie;
		AnimalDto animal;
		
		System.out.printf("----------------------------------%n");
		System.out.printf("      Ingesar nuevo animal        %n");
		System.out.printf("----------------------------------%n");
		System.out.printf("--Por favor completar los siguientes datos:--%n");
		

		while (tipoAnimal == null) {
			System.out.printf("Tipo de Animal:%n");
			System.out.printf("  1.Domestico%n");
			System.out.printf("  2.Salvaje%n");
			switch(sc.nextInt()) {
				case 1:
					tipoAnimal = TipoAnimal.DOMESTICO;
					break;
				case 2:
					tipoAnimal = TipoAnimal.SALVAJE;
					break;
				default:
					System.out.println("La opcion ingresada no existe");
			}
		}
		
		System.out.printf("Altura(en cm):%n");
		altura = sc.nextInt();
		
		System.out.printf("Peso(en g):%n");
		peso = sc.nextInt();
		
		
		while (fechaNac == null) {
			System.out.printf("Fecha de nacimiento (dd/mm/yyyy):%n");
			try {
				fechaNac = formateadorFecha.parse(sc.next());
			} catch (ParseException e) {
				System.out.println("Fecha Invalida!!");
			}
		}
		
		System.out.printf("Especie:%n");
		especie = sc.next();
		
		while (estado == null) {
			System.out.printf("Estado:%n");
			System.out.printf("  1.Saludable%n");
			System.out.printf("  2.Necesita atencion%n");
			
			switch(sc.nextInt()) {
				case 1:
					estado = EstadoAnimal.SALUDABLE;
					break;
				case 2:
					estado = EstadoAnimal.EN_TRATAMIENTO;
					break;
				default:
					System.out.println("Incorrecto, Ingrese alguna de las opciones disponibles");
			}
		}
		
		animal = new AnimalDto(altura, peso, fechaNac, especie, estado, tipoAnimal);
		
		this.controlador.cargarAnimal(animal);
		
		System.out.println("Animal cargado correctamente!");
		this.mostarMenu();
	}
	
	private void mostrarAnimales() {
		System.out.printf("----------------------------------------------------------------------------------------------%n");
		System.out.printf("| %-5s | %-10s | %-8s | %-20s | %-30s | %-8s | %-4s |%n", 
				"n°","Tipo", "Especie", "Estado", "Fecha Nac", "Altura", "Peso");
		System.out.printf("----------------------------------------------------------------------------------------------%n");
		
		List<AnimalDto> animales = this.controlador.listadoAnimales();
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
	
		
		int i = 1;
		for (AnimalDto animal : animales) {
			System.out.printf("| %-5s | %-10s | %-8s | %-20s | %-30s | %-8s | %-4s |%n", 
					i,animal.getTipo(), animal.getEspecie(), animal.getEstado(), formateadorFecha.format(animal.getFecha_nac()), 
					animal.getAltura(), animal.getPeso());
			i++;
		}
		
		System.out.printf("----------------------------------------------------------------------------------------------%n");
	
		System.out.printf("Ingrese el n° del animal que desea seleccionar(Otro valor para volver atras):");
		
		int animalSeleccionado = sc.nextInt();
		
		if ((animalSeleccionado >= 1) && (animalSeleccionado <= animales.size())){
			this.mostrarMenuAnimal(animales.get(animalSeleccionado - 1));
		}
		else {
			this.mostarMenu();
		}
	}
	
	private void mostrarMenuAnimal(AnimalDto animalDto) {
		int opcionIngresada;
		
		System.out.println();
		System.out.println();
		System.out.printf("--------------------------------Animal Seleccionado-------------------------------------------%n");
		System.out.printf("| %-10s | %-8s | %-20s | %-30s | %-8s | %-4s |%n", 
				"Tipo", "Especie", "Estado", "Fecha Nac", "Altura", "Peso");
		System.out.printf("| %-10s | %-8s | %-20s | %-30s | %-8s | %-4s |%n", 
				animalDto.getTipo(), animalDto.getEspecie(), animalDto.getEstado(), animalDto.getFecha_nac(), animalDto.getAltura(), 
				animalDto.getPeso());
		System.out.printf("----------------------------------------------------------------------------------------------%n");
		System.out.printf("1 - Exportar ficha medica         %n");
		System.out.printf("2 - Ver historia clinica          %n");
		
		opcionIngresada = sc.nextInt();
		switch (opcionIngresada) {
			case 1:
				System.out.println("MOSTRAR OPCIONES DE EXPORTACION FICHA MEDICA (PDF O EXCEL)");
				break;
			case 2:
				System.out.println("MOSTRAR HISTORIA CLINICA (EVOLUCION MEDICA, CHEQUEOS Y VISITAS)");
				break;
			default:
				this.mostarMenu();
		}
		
	}
	
	
	
	
	
}

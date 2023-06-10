package vistas;

import java.util.Scanner;

import controladores.AlarmaController;
import modelo.enumeraciones.TipoDeAlarma;

public class AlarmaCli {

	private AlarmaController controlador;
	private static Scanner sc;
	
	public AlarmaCli() {
		this.controlador = new AlarmaController();
		sc = new Scanner(System.in);
	}
	
	public void mostrarMenu() {
		System.out.printf("1 - Crear alarma            %n");
		System.out.printf("2 - Actualizar alarma       %n");
		System.out.printf("3 - Ver Alarmas             %n");
		
		
		
		
		
	}
	
	private void crearAlarma() {
		TipoDeAlarma tipo = null;
		int periodicidad;
		String[] accionesPosibles = {"Control de parásitos", "Colocar antiparasitarios", "Comprobar peso y tamaño", 
				"Chequear nutricion", "Colocar vacuna"};
		
		while (tipo == null) {
			System.out.printf("Tipo de Alarma:%n");
			System.out.println("1. Control%n");
			System.out.println("2. Tratamiento%n");
			switch(sc.nextInt()) {
				case 1:
					tipo = TipoDeAlarma.CONTROL;
				case 2:
					tipo = TipoDeAlarma.TRATAMIENTO;
				default:
					System.out.println("Opción incorrecta");
			}
		}
		
		System.out.printf("Ingrese la periodicidad (dias):");
		periodicidad =  sc.nextInt();
		
		
		
		
		
	}
	
	
}

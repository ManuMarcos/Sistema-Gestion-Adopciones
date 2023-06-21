package vistas;

import java.util.Scanner;

import controladores.AlarmaController;
import modelo.dto.AnimalDto;

public class AlarmaView {

	private AlarmaController controlador;
	private static Scanner sc;
	
	public void setController(AlarmaController controlador) {
		this.controlador = controlador;
	}
	
	public void mostrarMenu(AnimalDto animal) {
		System.out.printf("1 - Crear alarma            %n");
		System.out.printf("2 - Actualizar alarma       %n");
		System.out.printf("3 - Ver Alarmas             %n");
	}
	
	private void crearAlarma() {
		
	}
}

package vistas;

import java.util.Scanner;

import controladores.MenuPrincipalController;

public class MenuPrincipalCli {

	private MenuPrincipalController controlador;
	private static Scanner sc;
	
	public MenuPrincipalCli() {
		this.controlador = new MenuPrincipalController();
		sc = new Scanner(System.in);
	}
	
	
	public void mostrarMenu() {
		System.out.printf("----------------------------------%n");
		System.out.printf("        Refugio - Gud Boy         %n");
		System.out.printf("----------------------------------%n");
		
		
		
		
		
	}
	
	
}

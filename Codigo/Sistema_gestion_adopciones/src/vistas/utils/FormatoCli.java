package vistas.utils;

public class FormatoCli {
	private FormatoCli() {}; //static class
	
	public static void printCabecera(String nombre) {
		System.out.println("----------------------------------");
		System.out.printf(" %s%n", nombre);
		System.out.println("----------------------------------");
	}
	
	public static void printOpciones(String[] opciones) {
		for(int num=0; num<opciones.length; num++) {
			System.out.printf(" %d - %s%n", num+1,  opciones[num]);
		}
		System.out.println("----------------------------------");
	}
}

package vistas.utils;

public class FormatoCli {
	private FormatoCli() {
	} // static class

	public static void printCabecera(String nombre) {
		System.out.print("\033[32m");
		System.out.println("----------------------------------");
		System.out.printf(" %s%n", nombre);
		System.out.println("----------------------------------");
		System.out.print("\033[0m");
	}

	public static void printOpciones(String[] opciones) {
		System.out.print("\033[32m");
		for (int num = 0; num < opciones.length; num++) {
			System.out.printf(" %d - %s%n", num + 1, opciones[num]);
		}
		System.out.println("----------------------------------");
		System.out.print("\033[0m");
	}

	public static void esperaTruchanga() { // Es efecto visual de relleno. Si no gusta, se saca.
		System.out.print("Cargando");
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print('.');
		}
		System.out.println();
	}
}

package vistas.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IngresoCli {
	private static Scanner sc = new Scanner(System.in);
	private static String mailRegex = ".+@.+\\..+"; // algo@algo.algo, cubre poco, pero sirve para mostrar

	private IngresoCli() {
	}; // static class

	public static int solicitarOpcion(int cantidadDeOpciones) {
		System.out.print("Ingrese una opción: ");
		int opcion = -1;
		try {
			opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			opcion = -1;
		}
		while (opcion > cantidadDeOpciones || opcion < 1) {
			System.out.print("opción inválida. Ingrese una opción: ");
			sc.nextLine(); // clear
			try {
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				opcion = -1;
			}
		}
		return opcion;
	}

	public static String solicitarStringNoNulo(String mensaje) {
		System.out.print(mensaje);
		sc.nextLine();
		String input = sc.next();
		while (input.isEmpty()) {
			System.out.print("Ingreso inválido. " + mensaje);
			sc.nextLine();
			input = sc.next();
		}
		return input;
	}

	public static String solicitarEmail(String mensaje) {
		System.out.print(mensaje);
		String input = sc.next();
		while (input.isEmpty() || !input.matches(mailRegex)) {
			System.out.print("Ingreso inválido. " + mensaje);
			sc.nextLine();
			input = sc.next();
		}
		return input;
	}

	public static String solicitarStringNumericoNulo(String mensaje) {
		System.out.print(mensaje);
		String input = sc.next();
		int num = -1;
		try {
			num = Integer.parseInt(input);
		} catch (NumberFormatException exc) {
			num = -1;
		}
		while (input.isEmpty() || num == -1) {
			System.out.print("Ingreso inválido. " + mensaje);
			sc.nextLine();
			input = sc.next();
			try {
				num = Integer.parseInt(input);
			} catch (NumberFormatException exc) {
				num = -1;
			}
		}
		return input;
	}

	public static boolean solicitarSiNo(String mensaje) {
		System.out.print(mensaje);
		String input = sc.next();
		while (!input.equals("Y") && !input.equals("N")) {
			System.out.print("Ingreso inválido. " + mensaje);
			sc.nextLine();
			input = sc.next();
		}
		return input.equals("Y");
	}

	public static void pedirContinuar() {
		System.out.print("\033[33m");
		System.out.print("Presione Enter para continuar...");
		try {
			System.in.read();
			sc.nextLine();
		} catch (Exception e) {
			System.out.print(e);
		}
		System.out.print("\033[0m");
	}
}

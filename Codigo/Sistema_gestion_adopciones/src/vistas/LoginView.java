package vistas;

import java.util.Scanner;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;

public class LoginView {
	private static Scanner sc = new Scanner(System.in); //TODO: crear clase Input

	private LoginController controlador;
	
	public void mostrarMenuLogin() {
		System.out.println("----------------------------------");
		System.out.println("          Login                   ");
		System.out.println("----------------------------------");
		System.out.println(" 1 - Registrarse                  ");
		System.out.println(" 2 - Ingresar                     ");
		System.out.println(" 3 - Atrás                        ");
		System.out.println("----------------------------------");
		deMenuA(solicitarOpcionMenu());
	}
	
	public void mostrarRegistroUsuario() {
		UsuarioDto usuario = new UsuarioDto();	
		System.out.println("----------------------------------");
		System.out.println("          Nuevo Usuario           ");
		System.out.println("----------------------------------");
		System.out.print("Ingrese un nombre de usuario: ");
		usuario.usuario = sc.next();
		System.out.print("Ingrese una contraseña: ");
		usuario.contrasena = sc.next();
		System.out.println("Que tipo de usuario? 1: veterinario | 2: visitador");
		System.out.print("Ingrese una opción: ");
		int opcion = sc.nextInt();
		while(opcion != 1 && opcion != 2) {
			System.out.println("opción inválida");
			opcion = sc.nextInt();
		}
		usuario.tipoUsuario = (opcion == 1) ? TipoUsuario.VETERINARIO : TipoUsuario.VISITADOR;
		
		boolean res = controlador.registrarUsuario(usuario);
		if(res) {
			System.out.println("Usuario registrado exitosamente");
		} else {			
			System.out.println("Error al registrarse."); //TODO: manejo de errores...
		}
		mostrarMenuLogin();
	}

	private void mostrarIngresarUsuario() {
		// TODO Auto-generated method stub	
	}
	
	private int solicitarOpcionMenu() {
		System.out.print("Ingrese una opción: ");
		int opcion = sc.nextInt();
		while(opcion != 1 && opcion != 2 && opcion != 3) {
			System.out.println("opción inválida");
			opcion = sc.nextInt();
		}
		return opcion;
	}
	
	private void deMenuA(int opcion) {
		switch (opcion) {
		case 1:
			mostrarRegistroUsuario();
			break;
		case 2:
			mostrarIngresarUsuario();
			break;
		case 3: // TODO: flujo de navegacion hacia atras.
			System.out.print("chau");
		}
	}

	public void setControlador(LoginController controlador) {
		this.controlador = controlador;
	}
}

package vistas;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.utils.FormatoCli;
import vistas.utils.IngresoCli;

public class LoginView {
	private LoginController controlador;
	
	public void mostrarMenuLogin() {
		FormatoCli.printCabecera("Menú Login");
		String[] opciones = {"Registrarse", "Ingresar", "Atrás"};
		FormatoCli.printOpciones(opciones);
		deMenuA(IngresoCli.solicitarOpcion(opciones.length));
	}
	
	public void mostrarRegistroUsuario() {
		FormatoCli.printCabecera("Nuevo Usuario");

		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese un usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese una contraseña: ");
		usuario.tipoUsuario = this.solicitarOpcionTipoUsuario();
		
		boolean res = controlador.registrarUsuario(usuario);
		if(res) {
			System.out.println("Usuario registrado exitosamente");
		} else {			
			System.out.println("Error al registrarse.");
		}
		mostrarMenuLogin();
	}
	
	private TipoUsuario solicitarOpcionTipoUsuario() {
		System.out.println("Que tipo de usuario? 1: veterinario | 2: visitador");
		return (IngresoCli.solicitarOpcion(2) == 1) ? TipoUsuario.VETERINARIO : TipoUsuario.VISITADOR;
	}
	

	private void mostrarIngresarUsuario() {
		FormatoCli.printCabecera("Ingreso Usuario");
		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese su usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese su contraseña: ");
		
		boolean res = controlador.ingresarUsuario(usuario);
		if(res) {
			System.out.println("Ingreso exitoso");
			irAHome();
		} else {			
			System.out.println("Error al ingresar.");
			mostrarMenuLogin();
		}
	}
	
	private void irAHome() {
		//  TODO: ir a la siguiente pantalla en el flujo...
		System.out.print("TODO: ir a la siguiente pantalla en el flujo...");
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

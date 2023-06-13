package vistas;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class LoginView implements ICliView {
	
	private enum CodigosRetorno { 
		LOGIN_OK,
		LOGIN_ERROR,
		REGISTRO_OK,
		REGISTRO_ERROR,
		SALIR
	}
	
	private LoginController controlador;
	private static final String[] opciones = {"Registrarse", "Ingresar", "Salir"};
	private static final int opcion_Registrarse = 1;
	private static final int opcion_Ingresar = 2;
	private static final int opcion_Salir = 3;

	
	public void mostrarMenuLogin() {
		FormatoCli.printCabecera("Menú Login");
		FormatoCli.printOpciones(opciones);
	}
	
	public CodigosRetorno pedirOpcionesYProcesar() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		case opcion_Registrarse:
			return mostrarRegistroUsuario();
		case opcion_Ingresar:
			return mostrarIngresarUsuario();
		case opcion_Salir:
		default:
			return CodigosRetorno.SALIR;
		}
	}
	
	public CodigosRetorno mostrarRegistroUsuario() {
		FormatoCli.printCabecera("Nuevo Usuario");

		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese un usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese una contraseña: ");
		usuario.tipoUsuario = this.solicitarOpcionTipoUsuario();
		
		boolean res = controlador.registrarUsuario(usuario);
		if(res) {
			System.out.println("Usuario registrado exitosamente");
			return CodigosRetorno.REGISTRO_OK;
		} else {			
			System.out.println("Error al registrarse.");
			return CodigosRetorno.REGISTRO_ERROR;
		}
	}
	
	private TipoUsuario solicitarOpcionTipoUsuario() {
		System.out.println("Que tipo de usuario? 1: veterinario | 2: visitador");
		return (IngresoCli.solicitarOpcion(2) == 1) ? TipoUsuario.VETERINARIO : TipoUsuario.VISITADOR;
	}
	

	private CodigosRetorno mostrarIngresarUsuario() {
		FormatoCli.printCabecera("Ingreso Usuario");
		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese su usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese su contraseña: ");
		
		boolean res = controlador.ingresarUsuario(usuario);
		if(res) {
			System.out.println("Ingreso exitoso");
			return CodigosRetorno.LOGIN_OK;
		} else {			
			System.out.println("Error al ingresar.");
			return CodigosRetorno.LOGIN_ERROR;
		}
	}
	
	public void setControlador(LoginController controlador) {
		this.controlador = controlador;
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuLogin();
		LoginView.CodigosRetorno retCode = pedirOpcionesYProcesar();
		if(retCode == LoginView.CodigosRetorno.SALIR)
			return CliViewNames.BACK;
		if(retCode != LoginView.CodigosRetorno.LOGIN_OK) {
			return CliViewNames.STAY;
		}
		return CliViewNames.MENU_PRINCIPAL;
	}
}

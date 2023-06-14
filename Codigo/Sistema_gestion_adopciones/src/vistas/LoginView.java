package vistas;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class LoginView implements ICliView {
	private LoginController controlador;

	private static final String[] opciones = { "Registrarse", "Ingresar", "Salir" };
	private static final int opcion_Registrarse = 1;
	private static final int opcion_Ingresar = 2;
	private static final int opcion_Salir = 3;

	private void mostrarMenuLogin() {
		FormatoCli.printCabecera("Menú Login");
		FormatoCli.printOpciones(opciones);
	}

	private LoginController.CodigosRetorno pedirOpcionesYProcesar() {
		int opcion = IngresoCli.solicitarOpcion(opciones.length);
		switch (opcion) {
		case opcion_Registrarse:
			return mostrarRegistroUsuario();
		case opcion_Ingresar:
			return mostrarIngresarUsuario();
		case opcion_Salir:
			return LoginController.CodigosRetorno.SALIR;
		default:
			throw new RuntimeException("pedirOpcionesYProcesar > opcion invalida :[" + opcion + "]");
		}
	}

	private LoginController.CodigosRetorno mostrarRegistroUsuario() {
		FormatoCli.printCabecera("Nuevo Usuario");

		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese un usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese una contraseña: ");
		usuario.tipoUsuario = this.solicitarOpcionTipoUsuario();

		LoginController.CodigosRetorno res = controlador.registrarUsuario(usuario);
		if (res == LoginController.CodigosRetorno.REGISTRO_OK) {
			System.out.println("Usuario registrado exitosamente");
		} else {
			System.out.println("Error al registrarse.");
		}
		return res;
	}

	private TipoUsuario solicitarOpcionTipoUsuario() {
		System.out.println("Que tipo de usuario? 1: veterinario | 2: visitador");
		return (IngresoCli.solicitarOpcion(2) == 1) ? TipoUsuario.VETERINARIO : TipoUsuario.VISITADOR;
	}

	private LoginController.CodigosRetorno mostrarIngresarUsuario() {
		FormatoCli.printCabecera("Ingreso Usuario");
		UsuarioDto usuario = new UsuarioDto();
		usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese su usuario: ");
		usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese su contraseña: ");

		LoginController.CodigosRetorno res = controlador.ingresarUsuario(usuario);
		if (res == LoginController.CodigosRetorno.LOGIN_OK) {
			System.out.println("Ingreso exitoso");
		} else {
			System.out.println("Error al ingresar.");
		}
		return res;
	}

	public void setControlador(LoginController controlador) {
		this.controlador = controlador;
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuLogin();
		LoginController.CodigosRetorno retCode = pedirOpcionesYProcesar();
		if (retCode == LoginController.CodigosRetorno.SALIR)
			return CliViewNames.BACK;
		if (retCode != LoginController.CodigosRetorno.LOGIN_OK) {
			return CliViewNames.STAY;
		}
		return CliViewNames.MENU_PRINCIPAL;
	}
}

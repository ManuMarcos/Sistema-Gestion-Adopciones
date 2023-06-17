package vistas;

import java.util.ArrayList;
import java.util.List;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.ICliOption;
import vistas.utils.FormatoCli;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class LoginView implements ICliView {

	class OptionRegistroUsuario implements ICliOption {
		@Override
		public CliViewNames doAction() {
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
			return CliViewNames.STAY;
		}

		private TipoUsuario solicitarOpcionTipoUsuario() {
			System.out.println("Que tipo de usuario? 1: veterinario | 2: visitador");
			return (IngresoCli.solicitarOpcion(2) == 1) ? TipoUsuario.VETERINARIO : TipoUsuario.VISITADOR;
		}
	}

	class OptionLoginUsuario implements ICliOption {
		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Ingreso Usuario");
			UsuarioDto usuario = new UsuarioDto();
			usuario.usuario = IngresoCli.solicitarStringNoNulo("Ingrese su usuario: ");
			usuario.contrasena = IngresoCli.solicitarStringNoNulo("Ingrese su contraseña: ");

			LoginController.CodigosRetorno res = controlador.ingresarUsuario(usuario);
			if (res == LoginController.CodigosRetorno.LOGIN_OK) {
				System.out.println("Ingreso exitoso");
				return CliViewNames.MENU_PRINCIPAL;
			} else {
				System.out.println("Error al ingresar.");
				return CliViewNames.STAY;
			}
		}

	}

	private static final String[] nombresOpciones = { "Registrarse", "Ingresar", "Salir" };
	private List<ICliOption> opciones;
	private LoginController controlador;

	public void setControlador(LoginController controlador) {
		this.controlador = controlador;
	}

	public LoginView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionRegistroUsuario());
		opciones.add(new OptionLoginUsuario());
		opciones.add(new ICliOption.OptionBack());
	}

	private void mostrarMenuLogin() {
		FormatoCli.printCabecera("Menú Login");
		FormatoCli.printOpciones(nombresOpciones);
	}

	@Override
	public CliViewNames procesar() {
		mostrarMenuLogin();
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

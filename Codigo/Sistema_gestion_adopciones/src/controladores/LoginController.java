package controladores;

import modelo.Usuario;
import modelo.dto.UsuarioDto;
import vistas.LoginView;

public class LoginController {
	private LoginView vista;

	public enum CodigosRetorno {
		LOGIN_OK, LOGIN_ERROR, REGISTRO_OK, REGISTRO_ERROR, SALIR
	}

	
	public LoginController(LoginView vista) {
		this.vista = vista;
	}

	public LoginController.CodigosRetorno registrarUsuario(UsuarioDto usuarioData) {
		Usuario usuario = new Usuario(usuarioData);
		return usuario.registrar();
	}

	public LoginController.CodigosRetorno ingresarUsuario(UsuarioDto usuarioData) {
		Usuario usuario = new Usuario(usuarioData);
		return usuario.ingresar();
	}
}

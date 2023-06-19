package controladores;

import modelo.Usuario;
import modelo.Veterinario;
import modelo.Visitador;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.LoginView;

public class LoginController {
	private LoginView vista;

	public enum CodigosRetorno {
		LOGIN_OK, LOGIN_ERROR, REGISTRO_OK, REGISTRO_ERROR
	}

	
	public LoginController(LoginView vista) {
		this.vista = vista;
	}

	public LoginController.CodigosRetorno registrarUsuario(UsuarioDto usuarioData) {
		if (usuarioData.nombreUsuario.isEmpty() || usuarioData.contrasena.isEmpty())
			return LoginController.CodigosRetorno.REGISTRO_ERROR;
		Usuario usuario;
		if(usuarioData.tipoUsuario == TipoUsuario.VETERINARIO)
			usuario = new Veterinario(usuarioData);
		else
			usuario = new Visitador(usuarioData);
		return usuario.registrar();
	}

	public LoginController.CodigosRetorno ingresarUsuario(UsuarioDto usuarioData) {
		Usuario usuario = new Usuario(usuarioData);
		return usuario.ingresar();
	}
}

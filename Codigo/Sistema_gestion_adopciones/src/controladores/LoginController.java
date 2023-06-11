package controladores;

import modelo.Usuario;
import modelo.dto.UsuarioDto;
import vistas.LoginView;

public class LoginController {
	private LoginView vista;

	public LoginController(LoginView vista) {
		this.vista = vista;
	}
		
	public void mostrarMenuLogin() {
		vista.mostrarMenuLogin();
	}

	public boolean registrarUsuario(UsuarioDto usuarioData) {
		Usuario usuario = new Usuario(usuarioData);
		return usuario.registrar();
	}

	public boolean ingresarUsuario(UsuarioDto usuarioData) {
		Usuario usuario = new Usuario(usuarioData);
		return usuario.ingresar();
	}
}

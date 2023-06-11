package controladores;

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

	public boolean registrarUsuario(UsuarioDto usuario) {
		// TODO Auto-generated method stub
		return false;
	}
}

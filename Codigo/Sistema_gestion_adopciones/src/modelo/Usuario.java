package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.dto.UsuarioDto;

public class Usuario {	
	private UsuarioDto usuarioData;
	private IAutenticador auth;
	
	public Usuario(UsuarioDto usuarioData){
		this.usuarioData = usuarioData;
		this.auth = new AutenticadorFalso();
	}
	
	public boolean registrar() {
		auth.registrarUsuario(usuarioData);
		return guardarUsuario(usuarioData);
	}

	public boolean ingresar() {
		auth.iniciarSesion(usuarioData);
		return validarUsuarioExiste(usuarioData);
	}

	// { InMemory
	private static List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
	
	private static boolean guardarUsuario(UsuarioDto usuarioData) {
		for (UsuarioDto usuarioAlmacenado : usuarios) {
			if(usuarioData.usuario.equals(usuarioAlmacenado.usuario)) {
				// Usuario ya existente.
				return false;
			}
		}
		usuarios.add(usuarioData);
		return true;
	}
	
	private static boolean validarUsuarioExiste(UsuarioDto usuarioData) {
		for (UsuarioDto usuarioAlmacenado : usuarios) {
			if(usuarioData.equals(usuarioAlmacenado)) {
				return true;
			}
		}
		// usuario no existente
		return false;
	}
	// } InMemory

}

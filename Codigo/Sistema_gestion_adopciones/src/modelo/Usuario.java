package modelo;

import java.util.ArrayList;
import java.util.List;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;

public class Usuario {
	private UsuarioDto usuarioData;
	private IAutenticador auth;

	public Usuario(UsuarioDto usuarioData) {
		this.usuarioData = usuarioData;
		this.auth = new AutenticadorFalso();
	}

	public LoginController.CodigosRetorno registrar() {
		auth.registrarUsuario(usuarioData);
		return guardarUsuario(usuarioData);
	}

	public LoginController.CodigosRetorno ingresar() {
		auth.iniciarSesion(usuarioData);
		return validarUsuarioExiste(usuarioData);
	}

	// { InMemory
	private static List<UsuarioDto> usuarios = new ArrayList<UsuarioDto>();
	
	static {
		addHardcodeGordoAdminVeterinario();
		addHardcodeGordoAdminVisitador();
	}

	private static void addHardcodeGordoAdminVeterinario() {
		UsuarioDto admin = new UsuarioDto();
		admin.usuario = "admin";
		admin.contrasena = "admin";
		admin.tipoUsuario = TipoUsuario.VETERINARIO;
		usuarios.add(admin);
	}
	
	private static void addHardcodeGordoAdminVisitador() {
		UsuarioDto admin = new UsuarioDto();
		admin.usuario = "admin2";
		admin.contrasena = "admin2";
		admin.tipoUsuario = TipoUsuario.VETERINARIO;
		usuarios.add(admin);
	}

	private static LoginController.CodigosRetorno guardarUsuario(UsuarioDto usuarioData) {
		for (UsuarioDto usuarioAlmacenado : usuarios) {
			if (usuarioData.usuario.equals(usuarioAlmacenado.usuario)) {
				// Usuario ya existente.
				return LoginController.CodigosRetorno.REGISTRO_ERROR;
			}
		}
		usuarios.add(usuarioData);
		return LoginController.CodigosRetorno.REGISTRO_OK;
	}

	private static LoginController.CodigosRetorno validarUsuarioExiste(UsuarioDto usuarioData) {
		for (UsuarioDto usuarioAlmacenado : usuarios) {
			if (usuarioData.equals(usuarioAlmacenado)) {
				return LoginController.CodigosRetorno.LOGIN_OK;
			}
		}
		// usuario no existente
		return LoginController.CodigosRetorno.LOGIN_ERROR;
	}
	// } InMemory

}

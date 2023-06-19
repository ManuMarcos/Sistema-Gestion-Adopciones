package modelo;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;
import repositorios.UsuarioRepository;

public class Usuario {
	public String nombreUsuario;
	public String contrasena;
	public TipoUsuario tipoUsuario; // dejar? sacar?

	private IAutenticador auth;

	public boolean equals(Usuario u) {
		return this.nombreUsuario.equals(u.nombreUsuario);
	}

	public Usuario(UsuarioDto usuarioData) {
		this.nombreUsuario = usuarioData.nombreUsuario;
		this.contrasena = usuarioData.contrasena;
		this.tipoUsuario = usuarioData.tipoUsuario;
		this.auth = new AutenticadorFalso();
	}

	public LoginController.CodigosRetorno registrar() {
		auth.registrarUsuario(nombreUsuario, contrasena);
		boolean resultOk = UsuarioRepository.guardarUsuario(this);
		if (resultOk)
			return LoginController.CodigosRetorno.REGISTRO_OK;
		return LoginController.CodigosRetorno.REGISTRO_ERROR;
	}

	public LoginController.CodigosRetorno ingresar() {
		auth.iniciarSesion(nombreUsuario, contrasena);
		Usuario u = UsuarioRepository.obtenerUsuario(nombreUsuario);
		return (u == null) ? LoginController.CodigosRetorno.LOGIN_ERROR : LoginController.CodigosRetorno.LOGIN_OK;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

}

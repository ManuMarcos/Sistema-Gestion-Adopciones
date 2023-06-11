package modelo.dto;

import modelo.enumeraciones.TipoUsuario;

public class UsuarioDto {
	public String usuario;
	public String contrasena;
	public TipoUsuario tipoUsuario;

	public boolean equals(UsuarioDto u) {
		return this.usuario.equals(u.usuario) && this.contrasena.equals(u.contrasena);
	}
}
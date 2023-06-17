package modelo.dto;

import modelo.enumeraciones.TipoUsuario;

public class UsuarioDto {
	public String nombreUsuario;
	public String contrasena;
	public TipoUsuario tipoUsuario;

	public boolean equals(UsuarioDto u) {
		return this.nombreUsuario.equals(u.nombreUsuario) && this.contrasena.equals(u.contrasena);
	}
}
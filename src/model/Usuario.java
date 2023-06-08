package model;

public abstract class Usuario {
	private UsuarioDto usuario_contrasena;
	private IAutenticador autenticacion;
	
	public UsuarioDto getUsuario_contrasena() {
		return usuario_contrasena;
	}
	public void setUsuario_contrasena(UsuarioDto usuario_contrasena) {
		this.usuario_contrasena = usuario_contrasena;
	}
}

package modelo;

import modelo.dto.UsuarioDto;

public interface IAutenticador {
	public void registrarUsuario(UsuarioDto usuario);
	public void iniciarSesion(UsuarioDto usuario);
}

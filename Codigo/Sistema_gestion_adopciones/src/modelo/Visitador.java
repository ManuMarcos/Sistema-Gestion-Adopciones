package modelo;

import controladores.LoginController;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;

public class Visitador extends Usuario {
	public Visitador(UsuarioDto usuarioData) {
		super(usuarioData);
	}

	private String al_pedo_existe; // TODO: ver si eliminar Visitador y Veterinario...
	// y cambiarlo por un bool esVeterinario en Usuario ?

	public static UsuarioDto getVeterinario(String usuario) {
		return null;
	}
}

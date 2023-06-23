package modelo;

import modelo.dto.UsuarioDto;
import repositorios.UsuarioRepository;

public class Visitador extends Usuario {
	public Visitador(UsuarioDto usuarioData) {
		super(usuarioData);
	}

	public static Visitador getVisitador(String usuario) {
		Visitador v = null;
		try {
			v = (Visitador) UsuarioRepository.obtenerUsuario(usuario);
		} catch (ClassCastException e){
			v = null;
		}
		return v;
	}
}

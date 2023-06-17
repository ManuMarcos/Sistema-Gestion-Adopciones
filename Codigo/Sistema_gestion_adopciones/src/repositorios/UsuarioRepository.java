package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;
import modelo.Veterinario;
import modelo.Visitador;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;

public class UsuarioRepository {
	// In-Memory
	private static List<Usuario> usuarios = new ArrayList<>();

	static {
		addHardcodeGordoAdminVeterinario();
		addHardcodeGordoAdminVisitador();
	}

	private static void addHardcodeGordoAdminVeterinario() {
		UsuarioDto admin = new UsuarioDto();
		admin.nombreUsuario = "admin";
		admin.contrasena = "admin";
		admin.tipoUsuario = TipoUsuario.VETERINARIO;
		usuarios.add(new Veterinario(admin));
	}

	private static void addHardcodeGordoAdminVisitador() {
		UsuarioDto admin = new UsuarioDto();
		admin.nombreUsuario = "admin2";
		admin.contrasena = "admin2";
		admin.tipoUsuario = TipoUsuario.VISITADOR;
		usuarios.add(new Visitador(admin));
	}

	public static boolean guardarUsuario(Usuario u) {
		for (Usuario usuarioAlmacenado : usuarios) {
			if (u.equals(usuarioAlmacenado)) {
				// Usuario ya existente.
				return false;
			}
		}
		usuarios.add(u);
		return true;
	}

	public static Usuario obtenerUsuario(String nombreUsuario) {
		for (Usuario usuarioAlmacenado : usuarios) {
			if (usuarioAlmacenado.getNombreUsuario().equals(nombreUsuario)) {
				return usuarioAlmacenado;
			}
		}
		return null;
	}
}

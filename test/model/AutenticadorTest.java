package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutenticadorTest {

	@Test
	void Login() {
		Usuario u = new Veterinario();
		u.setUsuario_contrasena(new UsuarioDto("pepe", "p3p3"));
		IAutenticador autenticador = new IAutenticador() {
			
			@Override
			public void IniciarSesion(UsuarioDto usuario) {
				System.out.println("Stub Autenticador> TODO OK, DALE PARA ADELANTE NOMAS");
			}
		};
		autenticador.IniciarSesion(u.getUsuario_contrasena());
	}

}

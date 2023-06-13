package modelo;

import modelo.dto.UsuarioDto;

public class Veterinario extends Usuario {
	public Veterinario(UsuarioDto usuarioData) {
		super(usuarioData);
	}

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}

package controladores;

import modelo.Adopcion;
import modelo.Animal;
import modelo.Cliente;
import modelo.Visitador;
import modelo.dto.AltaAdopcionDto;
import modelo.dto.ClienteDto;
import modelo.dto.UsuarioDto;
import modelo.enumeraciones.TipoUsuario;

public class AdopcionController {

	public enum CodigosRetorno {
		ERROR_ALTA_CLIENTE_NO_ENCONTRADO,
		ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO,
		ERROR_ALTA_ANIMAL_NO_ENCONTRADO,
		ERROR_ALTA_NO_IMPLEMENTADA_DEL_TODO,
	}

	public CodigosRetorno crearAdopcion(AltaAdopcionDto data) {
		ClienteDto cliente = Cliente.buscarClientePorDocumento(data.documentoCliente);
		if(cliente == null)
			return CodigosRetorno.ERROR_ALTA_CLIENTE_NO_ENCONTRADO;
		UsuarioDto visitador = Visitador.getVeterinario(data.usuarioDelVisitador);
		if(visitador == null || visitador.tipoUsuario != TipoUsuario.VISITADOR)
			return CodigosRetorno.ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO;
		Animal animal = null; //TODO: necesito forma de conesguir el animal por ID
		Adopcion adopcion = new Adopcion(new Cliente(cliente), new Visitador(visitador), animal);
		//TODO: adopcion me debería hacer un throw si no cumplo las precondiciones...
		//adopcion.guardar();
		return CodigosRetorno.ERROR_ALTA_NO_IMPLEMENTADA_DEL_TODO;
	}

}

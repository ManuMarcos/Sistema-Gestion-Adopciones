package controladores;

import modelo.Adopcion;
import modelo.Animal;
import modelo.Cliente;
import modelo.Visitador;
import modelo.dto.AdopcionDto;
import modelo.dto.AltaAdopcionDto;
import modelo.enumeraciones.TipoUsuario;
import vistas.AdopcionView;

public class AdopcionController {
	private AdopcionView vista;

	public enum CodigosRetorno {
		ERROR_ALTA_CLIENTE_NO_ENCONTRADO, ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO, ERROR_ALTA_ANIMAL_NO_ENCONTRADO,
		ERROR_ALTA_CLIENTE_EXCEDE_LIMITE_ADOPCIONES, ALTA_OK, ERROR_ALTA_ANIMAL_NO_ADOPTABLE
	}

	public AdopcionController(AdopcionView vista) {
		this.vista = vista;
	}

	public CodigosRetorno crearAdopcion(AltaAdopcionDto data) {
		Cliente cliente = Cliente.buscarClientePorDocumento(data.documentoCliente);
		if (cliente == null)
			return CodigosRetorno.ERROR_ALTA_CLIENTE_NO_ENCONTRADO;
		Visitador visitador = Visitador.getVisitador(data.usuarioDelVisitador);
		if (visitador == null || visitador.tipoUsuario != TipoUsuario.VISITADOR)
			return CodigosRetorno.ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO;
		Animal animal = Animal.getAnimalHardCodeado(data.idAnimal);
		Adopcion adopcion = new Adopcion(cliente, visitador, animal);
		return adopcion.guardar();
	}

	public AdopcionDto buscarAdopcionPorID(String id) {
		return Adopcion.buscar(Integer.parseInt(id)).toDto();
	}

}

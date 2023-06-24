package controladores;

import java.util.ArrayList;
import java.util.List;

import modelo.Adopcion;
import modelo.Animal;
import modelo.Cliente;
import modelo.Usuario;
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
		Usuario visitador = Visitador.getVisitador(data.usuarioDelVisitador);
		if (visitador == null || visitador.tipoUsuario != TipoUsuario.VISITADOR)
			return CodigosRetorno.ERROR_ALTA_USUARIO_VISITADOR_NO_ENCONTRADO;
		Animal animal = new Animal().buscarAnimal(data.nroIngresoAnimal);
		Adopcion adopcion = new Adopcion(cliente, visitador, animal);
		return adopcion.guardar();
	}

	public List<AdopcionDto> buscarAdopcionesPorCliente(String documento) {
		Cliente cliente = Cliente.buscarClientePorDocumento(documento);
		var dtos = new ArrayList<AdopcionDto>();
		if (cliente != null) {
			var adopciones = cliente.getAdopcionesRegistradas();
			for (Adopcion adop : adopciones) {
				dtos.add(adop.toDto());
			}
		}
		return dtos;
	}

	public AdopcionDto buscarAdopcionesPorAnimal(String nroIngresoAnimal) {
		Animal animal = new Animal().buscarAnimal(Integer.parseInt(nroIngresoAnimal));
		if (animal == null)
			return null;
		Adopcion adopcion = animal.getAdopcion();
		if (adopcion == null)
			return null;
		return adopcion.toDto();
	}

	public void enviarNotificaciones(Usuario usu) {
		List<Animal> animales = new Animal().getAll();
		for (Animal ani : animales) {
			if (ani.getAdopcion() != null
					&& ani.getAdopcion().getVisitador().getNombreUsuario() == usu.getNombreUsuario()) {
				ani.getAdopcion().enviarNotificacion();
			}
		}

	}

}

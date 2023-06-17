package modelo;

import java.util.ArrayList;
import java.util.List;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import modelo.enumeraciones.Ocupacion;

public class Cliente {

	private ClienteDto clienteDatos;
	private List<Adopcion> adopciones;

	public Cliente(ClienteDto clienteDatos) {
		this.clienteDatos = clienteDatos;
		this.adopciones = new ArrayList<Adopcion>(); // TODO: buscar adopciones asociadas al cliente
	}

	public ClienteController.CodigosRetorno registrar() {
		return guardarCliente(this.getDto());
	}

	// { InMemory
	private static List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	static {
		addHardcodeManoloCliente();
	}

	private static void addHardcodeManoloCliente() {
		ClienteDto manolo = new ClienteDto(); // cliente #1 hardcodeado
		manolo.documento = "40777888";
		manolo.nombre = "Manolo" ;
		manolo.apellido= "García" ;
		manolo.email = "mgarcia@uade.edu.ar" ;
		manolo.estadoCivil  = "Casado" ;
		manolo.telefono  = "1144448888" ;
		manolo.setOcupacion(0);
		manolo.tieneOtrasMascotas= true ;
		manolo.motivoAdopta = "me gustan los animales" ;
		manolo.animalesDeInteres = new ArrayList<String>();
		manolo.animalesDeInteres.add("Perro");
		manolo.animalesDeInteres.add("Gato");
		clientes.add(manolo);
	}

	private static ClienteController.CodigosRetorno guardarCliente(ClienteDto clienteData) {
		if (clienteData.documento.isEmpty()) // seria la PK
			return ClienteController.CodigosRetorno.ALTA_CLIENTE_ERROR;
		for (ClienteDto clienteAlmacenado : clientes) {
			if (clienteData.documento.equals(clienteAlmacenado.documento)) {
				// Cliente ya existente.
				return ClienteController.CodigosRetorno.ALTA_CLIENTE_ERROR;
			}
		}
		clientes.add(clienteData);
		return ClienteController.CodigosRetorno.ALTA_CLIENTE_OK;
	}

	public static ClienteDto buscarClientePorDocumento(String documento) {
		for (ClienteDto clienteAlmacenado : clientes) {
			if (documento.equals(clienteAlmacenado.documento)) {
				return new Cliente(clienteAlmacenado).getDto();
			}
		}
		// cliente no existente
		return null;
	}
	// } InMemory

	public ClienteDto getDto() {
		return this.clienteDatos;
	}

}

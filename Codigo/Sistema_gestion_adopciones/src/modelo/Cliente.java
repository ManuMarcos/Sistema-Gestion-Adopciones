package modelo;

import java.util.ArrayList;
import java.util.List;

import controladores.ClienteController;
import modelo.dto.ClienteDto;

public class Cliente {

	private ClienteDto clienteDatos;
	private List<Adopcion> adopciones;

	public Cliente(ClienteDto clienteDatos) {
		this.clienteDatos = clienteDatos;
		this.adopciones = new ArrayList<Adopcion>(); //TODO: arreglar la "persistencia" de esto. Quizas usar Lista de IDs de adopcion en el DTO... No sé-
	}

	public ClienteController.CodigosRetorno registrar() {
		return guardarCliente(this.getDto());
	}

	// { InMemory
	private static List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	private static ClienteController.CodigosRetorno guardarCliente(ClienteDto clienteData) {
		for (ClienteDto clienteAlmacenado : clientes) {
			if(clienteData.documento.equals(clienteAlmacenado.documento)) {
				// Cliente ya existente.
				return ClienteController.CodigosRetorno.ALTA_CLIENTE_ERROR;
			}
		}
		clientes.add(clienteData);
		return ClienteController.CodigosRetorno.ALTA_CLIENTE_OK;
	}
	
	public static ClienteDto buscarClientePorDocumento(String documento) {
		for (ClienteDto clienteAlmacenado : clientes) {
			if(documento.equals(clienteAlmacenado.documento)) {
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

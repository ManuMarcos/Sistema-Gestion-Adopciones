package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.dto.ClienteDto;

public class Cliente {

	private ClienteDto clienteDatos;
	private List<Adopcion> adopciones;

	public Cliente(ClienteDto clienteDatos) {
		this.clienteDatos = clienteDatos;
		this.adopciones = new ArrayList<Adopcion>(); //TODO: arreglar la "persistencia" de esto. Quizas usar Lista de IDs de adopcion en el DTO... No sé-
	}

	public boolean registrar() {
		return guardarCliente(this.getDto());
	}

	// { InMemory
	private static List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	private static boolean guardarCliente(ClienteDto clienteData) {
		for (ClienteDto clienteAlmacenado : clientes) {
			if(clienteData.documento.equals(clienteAlmacenado.documento)) {
				// Cliente ya existente.
				return false;
			}
		}
		clientes.add(clienteData);
		return true;
	}
	
	public static Cliente buscarClientePorDocumento(String documento) {
		for (ClienteDto clienteAlmacenado : clientes) {
			if(documento.equals(clienteAlmacenado.documento)) {
				return new Cliente(clienteAlmacenado);
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

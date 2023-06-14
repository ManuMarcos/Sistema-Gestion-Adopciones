package controladores;

import modelo.Cliente;
import modelo.dto.ClienteDto;
import vistas.ClienteView;

public class ClienteController {
	private ClienteView vista;

	public enum CodigosRetorno {
		ALTA_CLIENTE_OK, ALTA_CLIENTE_ERROR, BUSCAR_CLIENTE_OK, BUSCAR_CLIENTE_NOT_FOUND, ATRAS
	}
	
	public ClienteController(ClienteView vista) {
		this.vista = vista;
	}

	public ClienteController.CodigosRetorno registrarCliente(ClienteDto clienteDatos) {
		Cliente cliente = new Cliente(clienteDatos);
		return cliente.registrar();
	}
	
	public ClienteDto buscarCliente(String documento) {
		return Cliente.buscarClientePorDocumento(documento).getDto();
	}
}

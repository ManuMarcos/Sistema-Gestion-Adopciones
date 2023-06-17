package controladores;

import modelo.Cliente;
import modelo.dto.ClienteDto;
import vistas.ClienteView;

public class ClienteController {
	private ClienteView vista;

	public enum CodigosRetorno {
		ALTA_CLIENTE_OK, ALTA_CLIENTE_ERROR
	}

	public ClienteController(ClienteView vista) {
		this.vista = vista;
	}

	public ClienteController.CodigosRetorno registrarCliente(ClienteDto clienteDatos) {
		Cliente cliente = new Cliente(clienteDatos);
		return cliente.registrar();
	}

	public ClienteDto buscarCliente(String documento) {
		Cliente c = Cliente.buscarClientePorDocumento(documento);
		if (c != null)
			return c.toDto();
		return null;
	}
}

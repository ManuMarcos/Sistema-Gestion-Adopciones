package controladores;

import modelo.Cliente;
import modelo.dto.ClienteDto;
import vistas.ClienteView;

public class ClienteController {
	private ClienteView vista;

	public ClienteController(ClienteView vista) {
		this.vista = vista;
	}

	public void mostrarMenuCliente() {
		vista.mostrarMenuCliente();
	}

	public boolean registrarCliente(ClienteDto clienteDatos) {
		Cliente cliente = new Cliente(clienteDatos);
		return cliente.registrar();
	}
	
	public ClienteDto buscarCliente(String documento) {
		return Cliente.buscarClientePorDocumento(documento).getDto();
	}
}

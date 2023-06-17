package repositorios;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.dto.ClienteDto;

public class ClienteRepository {
	// In-Memory
	private static List<Cliente> clientes = new ArrayList<>();
	static {
		addHardcodeManoloCliente();
	}

	private static void addHardcodeManoloCliente() {
		ClienteDto manolo = new ClienteDto(); // cliente #1 hardcodeado
		manolo.documento = "40777888";
		manolo.nombre = "Manolo";
		manolo.apellido = "García";
		manolo.email = "mgarcia@uade.edu.ar";
		manolo.estadoCivil = "Casado";
		manolo.telefono = "1144448888";
		manolo.setOcupacion(0);
		manolo.tieneOtrasMascotas = true;
		manolo.motivoAdopta = "me gustan los animales";
		manolo.animalesDeInteres = new ArrayList<String>();
		manolo.animalesDeInteres.add("Perro");
		manolo.animalesDeInteres.add("Gato");
		clientes.add(new Cliente(manolo));
	}

	public static boolean guardarCliente(Cliente c) {
		for (Cliente clienteAlmacenado : clientes) {
			if (c.getDocumento().equals(clienteAlmacenado.getDocumento())) {
				// Cliente ya existente.
				return false;
			}
		}
		clientes.add(c);
		return true;
	}

	public static Cliente obtenerCliente(String documento) {
		for (Cliente clienteAlmacenado : clientes) {
			if (documento.equals(clienteAlmacenado.getDocumento())) {
				return clienteAlmacenado;
			}
		}
		// cliente no existente
		return null;
	}
}

package modelo;

import java.util.ArrayList;
import java.util.List;

import controladores.ClienteController;
import modelo.dto.ClienteDto;
import modelo.enumeraciones.Ocupacion;
import repositorios.ClienteRepository;

public class Cliente {

	public String documento;
	public String nombre;
	public String apellido;
	public String email;
	public String estadoCivil;
	public String telefono;
	private Ocupacion ocupacion;
	public boolean tieneOtrasMascotas;
	public String motivoAdopta;
	public List<String> animalesDeInteres;

	private List<Adopcion> adopciones;

	public Cliente(ClienteDto data) {
		this.documento = data.documento;
		this.nombre = data.nombre;
		this.apellido = data.apellido;
		this.email = data.email;
		this.estadoCivil = data.estadoCivil;
		this.telefono = data.telefono;
		this.ocupacion = data.ocupacion;
		this.tieneOtrasMascotas = data.tieneOtrasMascotas;
		this.motivoAdopta = data.motivoAdopta;
		this.animalesDeInteres = new ArrayList<>(data.animalesDeInteres);
		this.adopciones = new ArrayList<Adopcion>();
	}

	public ClienteController.CodigosRetorno registrar() {
		if (getDocumento().isEmpty()) // seria la PK
			return ClienteController.CodigosRetorno.ALTA_CLIENTE_ERROR;
		boolean retOk = ClienteRepository.guardarCliente(this);
		if (retOk)
			return ClienteController.CodigosRetorno.ALTA_CLIENTE_OK;
		return ClienteController.CodigosRetorno.ALTA_CLIENTE_ERROR;
	}

	public static Cliente buscarClientePorDocumento(String documento) {
		Cliente c = ClienteRepository.obtenerCliente(documento);
		if (c == null)
			return null;
		return c;
	}

	public ClienteDto toDto() {
		ClienteDto cliente = new ClienteDto();
		cliente.documento = this.documento;
		cliente.nombre = this.nombre;
		cliente.apellido = this.apellido;
		cliente.email = this.email;
		cliente.estadoCivil = this.estadoCivil;
		cliente.telefono = this.telefono;
		cliente.ocupacion = this.ocupacion;
		cliente.tieneOtrasMascotas = this.tieneOtrasMascotas;
		cliente.motivoAdopta = this.motivoAdopta;
		cliente.animalesDeInteres = new ArrayList<>(this.animalesDeInteres);
		return cliente;
	}

	public String getDocumento() {
		return this.documento;
	}
	
	public int getAdopcionesRegistradas() {
		return adopciones.size();
	}

}

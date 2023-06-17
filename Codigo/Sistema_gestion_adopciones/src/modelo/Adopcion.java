package modelo;

import java.time.LocalDateTime;

import controladores.AdopcionController;
import modelo.dto.AdopcionDto;
import repositorios.AdopcionRepository;

public class Adopcion {
	private static int generador_id = 0;
	private int id;
	private Cliente cliente;
	private Visitador visitador;
	private Animal animal;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private NotificadorStrategy notificador;
	private boolean continuarConVisitas;

	public void finalizarSeguimiento() {
		continuarConVisitas = false;
	}

	public void cambiarNotificacion(NotificadorStrategy notificador) {
		this.notificador = notificador;
	}

	public Adopcion(Cliente cliente, Visitador visitador, Animal animal) {
		this.id = generador_id++;
		this.cliente = cliente;
		this.visitador = visitador;
		this.animal = animal;
		inicio = LocalDateTime.now();
		continuarConVisitas = true;
	}

	public AdopcionController.CodigosRetorno guardar() {
		if (cliente.getAdopcionesRegistradas() >= 2)
			return AdopcionController.CodigosRetorno.ERROR_ALTA_CLIENTE_EXCEDE_LIMITE_ADOPCIONES;
		if (!animal.esAdoptable())
			return AdopcionController.CodigosRetorno.ERROR_ALTA_ANIMAL_NO_ADOPTABLE;
		AdopcionRepository.guardar(this);
		return AdopcionController.CodigosRetorno.ALTA_OK;
	}
	
	public static Adopcion buscar(int id) {
		return AdopcionRepository.obtener(id);
	}
	
	public int getID() {
		return this.id;
	}

	public AdopcionDto toDto() {
		AdopcionDto data = new AdopcionDto();
		data.id = this.id;
		data.documentoCliente = this.cliente.getDocumento();
		data.idAnimal = this.animal.getId();
		return data;
	}
}

package modelo;

import java.time.LocalDateTime;

import controladores.AdopcionController;
import modelo.dto.AdopcionDto;
import modelo.enumeraciones.MedioNotificacion;
import modelo.notificador.FacadeNotificador;
import modelo.notificador.IEstrategiaNotificacion;
import modelo.notificador.Notificacion;
import repositorios.AdopcionRepository;

public class Adopcion {
	private static int generador_id = 0;
	private int id;
	private Cliente cliente;
	private Usuario visitador;
	private Animal animal;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private boolean continuarConVisitas;
	private MedioNotificacion medioNotificacion;

	public boolean finalizarSeguimiento() {
		if(!continuarConVisitas) {
			return false;
		}
		continuarConVisitas = false;
		return true;
	}
	
	public boolean getContinuarConVisitas() {
		return continuarConVisitas;
	}

	

	public Adopcion(Cliente cliente, Usuario visitador, Animal animal) {
		this.id = ++generador_id;
		this.cliente = cliente;
		this.visitador = visitador;
		this.animal = animal;
		this.medioNotificacion = MedioNotificacion.SMS;
		animal.setAdopcion(this);
		inicio = LocalDateTime.now();
		continuarConVisitas = true;
	}

	public AdopcionController.CodigosRetorno guardar() {
		if (cliente.getAdopcionesRegistradas().size() >= 2)
			return AdopcionController.CodigosRetorno.ERROR_ALTA_CLIENTE_EXCEDE_LIMITE_ADOPCIONES;
		this.cliente.addAdopcion(this);
		if (!animal.esAdoptable())
			return AdopcionController.CodigosRetorno.ERROR_ALTA_ANIMAL_NO_ADOPTABLE;
		this.animal.setAdopcion(this);
		AdopcionRepository.guardar(this);
		// TODO: update repository de cliente y Animal. No hace tanta falta, porque no
		// usamos persistencia real...
		// El animal que estoy consiguiendo no es de repository por ahora...
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

	public Usuario getVisitador() {
		return visitador;
	}

	public void enviarNotificacion() {
		Notificacion noti = new Notificacion(this.id, 20230626);
		FacadeNotificador facade = new FacadeNotificador();
		facade.notificar(noti, this.medioNotificacion);
		
	}
}

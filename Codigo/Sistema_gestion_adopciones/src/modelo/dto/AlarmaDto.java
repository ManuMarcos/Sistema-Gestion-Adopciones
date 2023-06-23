package modelo.dto;

import java.util.List;

import modelo.Accion;
import modelo.TipoAlarma;

public class AlarmaDto {

	private List<AccionDto> acciones;
	private int periodicidad;
	private TipoAlarma tipo;
	//Getters

	public int getPeriodicidad() {
		return periodicidad;
	}

	public TipoAlarma getTipo() {
		return this.tipo;
	}

	public List<AccionDto> getAcciones() {
		return acciones;
	}

	//Methods
	public AlarmaDto(TipoAlarma tipo, List<Accion> acciones, int periodicidad) {
		for (Accion accion : acciones) {
			this.acciones.add(accion.toDto());
		}
		this.tipo=tipo;
		this.periodicidad = periodicidad;
	}
}

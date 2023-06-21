package modelo.dto;

import java.util.List;

import modelo.Accion;

public class AlarmaDto {

	private List<AccionDto> acciones;
	private int periodicidad;
	
	public AlarmaDto(List<Accion> acciones, int periodicidad) {
		for (Accion accion : acciones) {
			this.acciones.add(accion.toDto());
		}
		this.periodicidad = periodicidad;
	}
}

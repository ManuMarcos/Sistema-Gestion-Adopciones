package modelo;

import java.util.Date;
import java.util.List;

import modelo.dto.AlarmaDto;


public class Alarma {

	private TipoAlarma tipo;
	private int periodicidad;
	
	public Alarma(TipoAlarma tipo, int periodicidad) {
		this.tipo = tipo;
		this.periodicidad = periodicidad;
	}
	
	public void atender() {
		if (tipo instanceof Tratamiento) {
			Tratamiento tratamiento = (Tratamiento) tipo;
		}
		else if (tipo instanceof Control) {
			//CASTEAR Y HACER ALGO
		}
	}
	
	public void agregarAccion(String nombre) {
		this.tipo.agregarAccion(nombre);
	}
	
	public void enviar() {
		//ESTA ALARMA VA A USAR EL NOTIFICADOR CON LA ESTRATEGIA DE ENVIO PUSH
	}
	
	public AlarmaDto toDto() {
		return new AlarmaDto(this.tipo,tipo.getAcciones(), periodicidad);
	}
}

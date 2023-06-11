package modelo.dto;

import java.util.List;
import modelo.enumeraciones.Ocupacion;

public class ClienteDto {
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

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(int ocupacion) {
		switch (ocupacion) {
		case 1:
			this.ocupacion = Ocupacion.EMPLEADO;
			break;
		case 2:
			this.ocupacion = Ocupacion.ESTUDIANTE;
			break;
		default:
			this.ocupacion = Ocupacion.OTROS;
			break;
		}
	}
}

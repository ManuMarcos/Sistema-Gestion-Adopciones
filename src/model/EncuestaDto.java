package model;

public class EncuestaDto {
	private Calificacion estadoAnimal;
	private Calificacion limpiezaLugar;
	private Calificacion ambiente;
	
	public EncuestaDto(Calificacion estadoAnimal, Calificacion limpiezaLugar, Calificacion ambiente) {
		super();
		this.estadoAnimal = estadoAnimal;
		this.limpiezaLugar = limpiezaLugar;
		this.ambiente = ambiente;
	}

	public Calificacion getEstadoAnimal() {
		return estadoAnimal;
	}

	public void setEstadoAnimal(Calificacion estadoAnimal) {
		this.estadoAnimal = estadoAnimal;
	}

	public Calificacion getLimpiezaLugar() {
		return limpiezaLugar;
	}

	public void setLimpiezaLugar(Calificacion limpiezaLugar) {
		this.limpiezaLugar = limpiezaLugar;
	}

	public Calificacion getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Calificacion ambiente) {
		this.ambiente = ambiente;
	}
	
	
}
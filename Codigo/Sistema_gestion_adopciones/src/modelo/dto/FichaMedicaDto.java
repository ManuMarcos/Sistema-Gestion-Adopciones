package modelo.dto;

import java.util.Date;

public class FichaMedicaDto {

	private String tipoAnimal;
	private String especie;
	private int peso;
	private int altura;
	private Date fechaNac;
	private String estado;
	
	
	
	
	public FichaMedicaDto(String tipoAnimal, String especie, int peso, int altura, Date fechaNac, String estado) {
		this.tipoAnimal = tipoAnimal;
		this.especie = especie;
		this.peso = peso;
		this.altura = altura;
		this.fechaNac = fechaNac;
		this.estado = estado;
	}
	public String getTipoAnimal() {
		return tipoAnimal;
	}
	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}

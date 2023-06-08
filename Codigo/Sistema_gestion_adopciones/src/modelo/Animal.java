package modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Animal {

	private int altura;
	private int peso;
	private Date fecha_nac;
	private String especie;
	private EstadoAnimal estado;
	private TipoAnimal tipo;
	private List<Alarma> alarmas;
	private FichaMedica fichaMedica;
	
	public Animal(int altura, int peso, Date fecha_nac, String especie, EstadoAnimal estado, 
			TipoAnimal tipo) {
		this.altura = altura;
		this.peso = peso;
		this.fecha_nac = fecha_nac;
		this.especie = especie;
		this.estado = estado;
		this.tipo = tipo;
		this.alarmas = new ArrayList<Alarma>();
		this.fichaMedica = new FichaMedica(this);
	}
	
	
	
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Date getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public EstadoAnimal getEstado() {
		return estado;
	}
	public void setEstado(EstadoAnimal estado) {
		this.estado = estado;
	}
	public TipoAnimal getTipo() {
		return tipo;
	}
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}
	public List<Alarma> getAlarmas() {
		return alarmas;
	}
	public void setAlarmas(List<Alarma> alarmas) {
		this.alarmas = alarmas;
	}
	
	
}

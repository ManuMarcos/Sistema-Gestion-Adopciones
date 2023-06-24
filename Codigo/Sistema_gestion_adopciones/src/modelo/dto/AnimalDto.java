package modelo.dto;

import java.time.LocalDate;
import java.util.Date;

import modelo.Animal;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

public class AnimalDto {

	private int nroIngreso;
	private int altura;
	private int peso;
	private LocalDate fecha_nac;
	private String especie;
	private EstadoAnimal estado;
	private TipoAnimal tipo;
	
	
	public AnimalDto() {};
	
	
	//Constructor sin Id
	public AnimalDto(int altura, int peso, LocalDate fecha_nac, String especie, EstadoAnimal estado, TipoAnimal tipo) {
		this.altura = altura;
		this.peso = peso;
		this.fecha_nac = fecha_nac;
		this.especie = especie;
		this.estado = estado;
		this.tipo = tipo;
	}
	
	//Constructor con Id
	public AnimalDto(int nroIngreso, int altura, int peso, LocalDate fecha_nac, String especie, EstadoAnimal estado,
			TipoAnimal tipo) {
		this.nroIngreso = nroIngreso;
		this.altura = altura;
		this.peso = peso;
		this.fecha_nac = fecha_nac;
		this.especie = especie;
		this.estado = estado;
		this.tipo = tipo;
	}
	
	public Animal toEntity() {
		return new Animal(nroIngreso, altura, peso, fecha_nac, especie, estado, tipo);
	}
	

	public int getNroIngreso() {
		return nroIngreso;
	}

	public void setNroIngreso(int id) {
		this.nroIngreso = id;
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
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(LocalDate fecha_nac) {
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
	
	
}

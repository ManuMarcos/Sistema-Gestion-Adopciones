package modelo;

import java.util.Date;
import java.util.List;

import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

import java.util.ArrayList;

public class Animal {

	private int id;
	private static int generador = 1;
	private int altura;
	private int peso;
	private Date fecha_nac;
	private String especie;
	private EstadoAnimal estado;
	private TipoAnimal tipo;
	private List<Alarma> alarmas;
	private FichaMedica fichaMedica;
	private Adopcion adopcion;

	public Animal(int altura, int peso, Date fecha_nac, String especie, EstadoAnimal estado, TipoAnimal tipo) {
		this.altura = altura;
		this.peso = peso;
		this.fecha_nac = fecha_nac;
		this.especie = especie;
		this.estado = estado;
		this.tipo = tipo;
		this.alarmas = new ArrayList<Alarma>();
		this.fichaMedica = new FichaMedica(this);
		this.id = generador;
		generador += 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FichaMedica getFichaMedica() {
		return fichaMedica;
	}

	public void setFichaMedica(FichaMedica fichaMedica) {
		this.fichaMedica = fichaMedica;
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

	public AnimalDto toDto() {
		return new AnimalDto(this.getId(), this.getAltura(), this.getPeso(), this.getFecha_nac(), this.getEspecie(),
				this.getEstado(), this.getTipo());
	}

	public boolean esAdoptable() {
		return this.estado == EstadoAnimal.SALUDABLE && this.tipo == TipoAnimal.DOMESTICO && adopcion == null;
	}

	// TODO: agrego esto porque necesito poder conseguir un animal por id (o alguna
	// PK) para avanzar. despues implementarlo bien...
	static Animal a = new Animal(60, 60, new Date(), "Gato", EstadoAnimal.SALUDABLE, TipoAnimal.DOMESTICO);

	public static Animal getAnimalHardCodeado(int id) {
		if (id == 1)
			return a;
		else
			return null;
	}

	public void setAdopcion(Adopcion adopcion) {
		this.adopcion = adopcion;
	}

	public Adopcion getAdopcion() {
		return this.adopcion;
	}
}

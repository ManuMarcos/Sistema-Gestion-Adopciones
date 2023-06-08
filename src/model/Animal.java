package model;

public class Animal {
	private String nombre;
	private float altura;
	private float peso;
	private String especie;
	private boolean esSalvaje;
	private boolean estaEnTratamiento;
	private Adopcion adopcion;
	
	public boolean esAdoptable() {
		return !esSalvaje && !estaEnTratamiento;
	}
}

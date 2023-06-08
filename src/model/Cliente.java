package model;

import java.util.List;

public class Cliente {
	private String nombre;
	private String apellido;
	private String email;
	private String estadoCivil;
	private String telefono;
	private Ocupacion ocupacion;
	private boolean tieneOtrasMascotas;
	private String motivoAdopta;
	private List<String> animalesDeInteres;
	private List<Adopcion> adopciones; /// restringidas a 2. controlar eso en setter o el metodo que se agregue...
}

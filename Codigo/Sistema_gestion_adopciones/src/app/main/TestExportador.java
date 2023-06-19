package app.main;

import java.util.Date;

import modelo.Animal;
import modelo.FichaMedica;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import modelo.exportacion.FacadeExportador;
import modelo.exportacion.FormatoExportacion;

public class TestExportador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FacadeExportador exportador = new FacadeExportador();
		exportador.exportar(new FichaMedica(new Animal(190, 85, new Date(), "Perro", 
				EstadoAnimal.EN_TRATAMIENTO,TipoAnimal.DOMESTICO)), FormatoExportacion.PDF, "fichaMedica");
		
		
		
	}

}

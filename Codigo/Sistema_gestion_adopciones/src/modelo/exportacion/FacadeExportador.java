package modelo.exportacion;

public class FacadeExportador {

	public String exportar(IExportable exportable, FormatoExportacion formato, String nombreArchivo) {
		IEstrategiaExportacion estrategiaExportacion = FactoryEstrategiaExportacion.crearEstrategia(formato, nombreArchivo);
		Exportador exportador = new Exportador(estrategiaExportacion);
		exportador.setExportable(exportable);
		return exportador.exportar();
	}
	
	
}

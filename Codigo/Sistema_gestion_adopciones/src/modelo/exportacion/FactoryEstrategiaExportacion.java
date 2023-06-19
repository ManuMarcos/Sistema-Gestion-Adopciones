package modelo.exportacion;

public class FactoryEstrategiaExportacion {

	public static IEstrategiaExportacion crearEstrategia(FormatoExportacion formato, String nombreArchivo) {
		IEstrategiaExportacion estrategia = null;
		switch (formato) {
			case EXCEL:
				//FALTA CONSTRUCTOR CON ADAPTER ADENTRO
				estrategia = new ExportacionExcel(new AdapterApachePoi(nombreArchivo));
				break;
			case PDF:
				//FALTA CONSTRUCTOR CON ADAPTER ADENTRO
				estrategia = new ExportacionPdf(new AdapterApachePdfBox(nombreArchivo));
				break;
		}
		return estrategia;
	}
}

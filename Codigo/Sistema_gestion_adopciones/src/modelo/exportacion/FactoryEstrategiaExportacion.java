package modelo.exportacion;

public class FactoryEstrategiaExportacion {

	public static IEstrategiaExportacion crearEstrategia(FormatoExportacion formato, String nombreArchivo) {
		IEstrategiaExportacion estrategia = null;
		switch (formato) {
			case EXCEL:
				estrategia = new ExportacionExcel(new AdapterApachePoi(nombreArchivo));
				break;
			case PDF:
				estrategia = new ExportacionPdf(new AdapterApachePdfBox(nombreArchivo));
				break;
		}
		return estrategia;
	}
}

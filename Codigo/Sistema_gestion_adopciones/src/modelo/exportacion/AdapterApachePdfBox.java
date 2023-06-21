package modelo.exportacion;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import config.Config;

public class AdapterApachePdfBox implements IAdapterExportacionPdf {

	private String nombreArchivo;

	public AdapterApachePdfBox(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	@Override
	public String exportar(IExportable exportable) {
		// TODO Auto-generated method stub
		
		PDDocument doc = new PDDocument();
        PDPage myPage = new PDPage();
        doc.addPage(myPage);
        try {
            PDPageContentStream cont = new PDPageContentStream(doc, myPage);
            cont.beginText();
            cont.setFont(PDType1Font.TIMES_ROMAN, 12);
            cont.setLeading(14.5f);
            cont.newLineAtOffset(25, 700);
            agregarDatos(cont, exportable.datos());

            cont.endText();
            cont.close();
            doc.save(rutaCompletaArchivo());
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

		System.out.println("Documento Exportado correctamente");
		return rutaCompletaArchivo();

	}

	private String rutaCompletaArchivo() {
		return Config.RUTA_EXPORTACION + this.nombreArchivo + ".pdf";
	}

	private void agregarDatos(PDPageContentStream pagina, Map<String, List<String>> datos) throws IOException {
		for (Map.Entry<String, List<String>> entry : datos.entrySet()) {
			pagina.newLine();
			String datosDeLaFila = String.join(", ", entry.getValue());
			pagina.showText(datosDeLaFila);
		}
	}

}

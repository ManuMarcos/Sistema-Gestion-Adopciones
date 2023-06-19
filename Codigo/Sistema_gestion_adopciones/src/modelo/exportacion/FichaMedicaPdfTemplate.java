package modelo.exportacion;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import modelo.FichaMedica;
import modelo.dto.FichaMedicaDto;
import modelo.utils.Utils;

public class FichaMedicaPdfTemplate {

	private IExportable exportable;
	private PDFont fuente;


	public FichaMedicaPdfTemplate(IExportable exportable) {
		this.exportable = exportable;
		fuente = PDType1Font.HELVETICA_BOLD;
	}
	
	public PDPageContentStream escribirPagina(PDDocument documento, PDPage pagina) throws IOException {
		//Este contentStream va a ir guardando lo que se va a escribir en el archivo
		int anchoPagina = (int) pagina.getTrimBox().getWidth();
		int altoPagina = (int) pagina.getTrimBox().getHeight();
		PDPageContentStream contentStream;
		
		contentStream = new PDPageContentStream(documento, pagina);
		
		//Header
		contentStream.setNonStrokingColor(new Color(239, 196, 240));
		contentStream.addRect(0, altoPagina - 60, anchoPagina, 90);
		contentStream.fill();
		
		//Titulo
		contentStream.beginText();
		contentStream.setFont(fuente, 40);
		contentStream.setNonStrokingColor(Color.BLACK);
		String titulo = "Ficha Medica";
		float anchoTitulo = fuente.getStringWidth(titulo) / 1000 * 40;
		contentStream.newLineAtOffset((anchoPagina - anchoTitulo) / 2, altoPagina - 55);
		contentStream.showText(titulo);
		
		//Datos
		contentStream.newLineAtOffset(-100, 0);
		contentStream.setLeading(20f);
		contentStream.newLine();
		this.agregarDatos(contentStream, this.exportable.datos());
		
		contentStream.endText();
			
		//Cierro el contentStream
		contentStream.close();

		return contentStream;
	}
	
	private void agregarDatos(PDPageContentStream pagina, Map<String, List<String>> datos) throws IOException {
		pagina.setFont(fuente, 20);
		pagina.setNonStrokingColor(Color.BLACK);
		for (Map.Entry<String, List<String>> entry : datos.entrySet()) {
            pagina.newLine();
            String datosDeLaFila = entry.getKey() + ": " + entry.getValue();
            pagina.showText(datosDeLaFila);
        }
    }
	
}

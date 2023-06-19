package modelo.exportacion;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import config.Config;
import modelo.FichaMedica;

public class AdapterApachePdfBox implements IAdapterExportacionPdf{

	private String nombreArchivo;
	
	
	public AdapterApachePdfBox(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	@Override
	public String exportar(IExportable exportable) {
		// TODO Auto-generated method stub
		
		//Crea un nuevo documento vacio
		PDDocument documento = new PDDocument();
		
		
		
		//Crea una nueva pagina en blanco y se la agrega al documento
		PDRectangle tamanoPagina = PDRectangle.A4;
		PDPage pagina = new PDPage(tamanoPagina);
		documento.addPage(pagina);

		//Guarda el nuevo documento creado
		try {
			//Se crea un template para la Ficha Medica (actualmente no es escalable esto)
			FichaMedicaPdfTemplate fichaMedicaTemplate = new FichaMedicaPdfTemplate((FichaMedica) exportable);
			fichaMedicaTemplate.escribirPagina(documento, pagina);
			documento.save(this.rutaCompletaArchivo());
			documento.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Documento Exportado correctamente");
		return rutaCompletaArchivo();
	}
	
	private String rutaCompletaArchivo() {
		return Config.RUTA_EXPORTACION + this.nombreArchivo + ".pdf";
	}
	
	
	

}

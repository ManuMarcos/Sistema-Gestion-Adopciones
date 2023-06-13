package modelo;

public class FormatoPdf implements IFormatoStrategy{

	private AdapterFormatoPDF adapter;

	FormatoPdf() {
		adapter = new AdapterFormatoPDF();
	}
	
	@Override
	public void exportar(FichaMedica fm) {
		adapter.exportar(fm);
	}
	
	
}

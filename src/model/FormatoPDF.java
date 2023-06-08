package model;

public class FormatoPDF implements IFormatoStrategy {
	private AdapterFormatoPDF adapter;
	
	public FormatoPDF() {
		adapter = new AdapterFormatoPDF();
	}
	
	@Override
	public void exportar(FichaMedica fm) {
		adapter.exportar(fm);
	}
}

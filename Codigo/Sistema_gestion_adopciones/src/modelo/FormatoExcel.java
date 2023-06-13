package modelo;

public class FormatoExcel implements IFormatoStrategy{

	private AdapterFormatoExcel adapter;
	
	public FormatoExcel() {
		adapter = new AdapterFormatoExcel();
	}
	
	@Override
	public void exportar(FichaMedica fm) {
		adapter.exportar(fm);
	}

}

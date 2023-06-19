package modelo.exportacion;

public class ExportacionExcel implements IEstrategiaExportacion{

	private IAdapterExportacionExcel adapter;
	
	public ExportacionExcel(IAdapterExportacionExcel adapter) {
		this.adapter = adapter;
	}

	@Override
	public String exportar(IExportable exportable) {
		// TODO Auto-generated method stub
		return this.adapter.exportar(exportable);
	}

}

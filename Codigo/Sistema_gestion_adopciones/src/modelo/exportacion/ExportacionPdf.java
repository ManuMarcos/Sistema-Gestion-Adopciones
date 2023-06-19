package modelo.exportacion;

public class ExportacionPdf implements IEstrategiaExportacion{

	private IAdapterExportacionPdf adapter;
	
	public ExportacionPdf(IAdapterExportacionPdf adapter) {
		this.adapter = adapter;
	}

	@Override
	public String exportar(IExportable exportable) {
		// TODO Auto-generated method stub
		return this.adapter.exportar(exportable);
	}

}

package modelo.exportacion;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AdapterApachePoi implements IAdapterExportacionExcel{

	private String nombreArchivo;
	//private XSSFWorkbook workbook;
	
	public AdapterApachePoi(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	@Override
	public String exportar(IExportable exportable) {
		// TODO Auto-generated method stub
		return null;
	}

}

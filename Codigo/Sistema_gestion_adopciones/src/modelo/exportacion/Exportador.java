package modelo.exportacion;

public class Exportador {

	private IExportable exportable;
	private IEstrategiaExportacion estrategia;
	
	public Exportador(IEstrategiaExportacion estrategia) {
		this.estrategia = estrategia;
	}

	public String exportar() {
		return this.estrategia.exportar(this.exportable);
	}
	
	public void cambiarEstrategia(IEstrategiaExportacion nueva) {
		this.estrategia = nueva;
	}

	public void setExportable(IExportable exportable) {
		this.exportable = exportable;
	}
	
}

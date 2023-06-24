package excepciones;

public class HandledException extends Exception{

	private CodigoError codigo;
	
	public HandledException(CodigoError codigo, String mensaje) {
		super(mensaje);
		this.setCodigo(codigo);
	}

	public HandledException(CodigoError codigo, String mensaje, Throwable cause) {
		super(mensaje, cause);
		this.setCodigo(codigo);
	}
	
	
	public CodigoError getCodigo() {
		return codigo;
	}

	public void setCodigo(CodigoError codigo) {
		this.codigo = codigo;
	}
	
	
	
	
}

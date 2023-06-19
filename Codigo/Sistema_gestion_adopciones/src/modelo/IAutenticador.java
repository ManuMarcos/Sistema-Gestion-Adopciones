package modelo;

public interface IAutenticador {
	public void registrarUsuario(String usuario, String password);
	public void iniciarSesion(String usuario, String password);
}

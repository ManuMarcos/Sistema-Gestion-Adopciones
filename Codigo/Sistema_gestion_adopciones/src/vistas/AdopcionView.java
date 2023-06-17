package vistas;

import java.util.ArrayList;
import java.util.List;

import controladores.AdopcionController;
import modelo.dto.AltaAdopcionDto;
import vistas.enumeraciones.CliViewNames;
import vistas.utils.FormatoCli;
import vistas.utils.ICliOption;
import vistas.utils.ICliView;
import vistas.utils.IngresoCli;

public class AdopcionView implements ICliView {

	class OptionNuevaAdopcion implements ICliOption {
		@Override
		public CliViewNames doAction() {
			FormatoCli.printCabecera("Nueva Adopción");
			AltaAdopcionDto data = new AltaAdopcionDto();
			data.usuarioDelVisitador = IngresoCli.solicitarStringNoNulo("Ingrese el usuario del visitador: ");
			data.documentoCliente = IngresoCli.solicitarStringNumericoNulo("Documento del cliente: ");
			data.idAnimal = Integer.parseInt(IngresoCli.solicitarStringNoNulo("Ingrese identificador del Animal: "));
			controlador.crearAdopcion(data);
			System.out.println("Work in progres...");
			return CliViewNames.STAY;
		}
	}

	class OptionConsultarAdopcion implements ICliOption {
		@Override
		public CliViewNames doAction() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	//

	private static final String[] nombresOpciones = { "Nueva", "Consultar", "Atrás" };
	private List<ICliOption> opciones;
	private AdopcionController controlador;

	public void setControlador(AdopcionController controlador) {
		this.controlador = controlador;
	}

	public AdopcionView() {
		opciones = new ArrayList<>();
		opciones.add(new OptionNuevaAdopcion());
		opciones.add(new OptionConsultarAdopcion());
		opciones.add(new ICliOption.OptionBack());
	}

	@Override
	public CliViewNames procesar() {
		FormatoCli.printCabecera("Menú Adopciones");
		FormatoCli.printOpciones(nombresOpciones);
		int opcion = IngresoCli.solicitarOpcion(nombresOpciones.length);
		return opciones.get(opcion - 1).doAction();
	}
}

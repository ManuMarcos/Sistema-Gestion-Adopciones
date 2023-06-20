package vistas.utils;

import java.util.Stack;

import vistas.enumeraciones.CliViewNames;

public abstract class CliViewRunner {
	private Stack<ICliView> stackCliView;
	
	private void clearPantalla() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	protected CliViewRunner() {
		stackCliView = new Stack<>();
	}
	
	protected void setFirstView(ICliView v) {
		stackCliView.push(v);
	}

	protected void run() {
		clearPantalla();
		while(!stackCliView.isEmpty()) {
			CliViewNames nextName = stackCliView.peek().procesar();
			ICliView next =  mapCliViewName(nextName);
			if(next != null) {
				stackCliView.push(next);
			} else if(nextName == CliViewNames.BACK) {
				stackCliView.pop();
			}
			clearPantalla();
		}
	}
	
	// metodo que mapea CliViewNames -> ICliView de la app
	protected abstract ICliView mapCliViewName(CliViewNames next);

}

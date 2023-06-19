package vistas.utils;

import vistas.enumeraciones.CliViewNames;

public interface ICliOption {	
	public CliViewNames doAction();
	
	public class OptionBack implements ICliOption {
		@Override
		public CliViewNames doAction() {
			return CliViewNames.BACK;
		}
	}
}

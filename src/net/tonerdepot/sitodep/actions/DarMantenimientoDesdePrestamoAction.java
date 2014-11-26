package net.tonerdepot.sitodep.actions;

import net.tonerdepot.sitodep.modelo.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

public class DarMantenimientoDesdePrestamoAction extends ViewBaseAction 
				implements IHideActionAction {
	
	private boolean hideAction = false;		//para indicar si la accion se ocultara

	@Override
	public void execute() throws Exception {
		Prestamo prestamo = XPersistence.getManager().find(
				Prestamo.class, getView().getValue("conduce"));
		
		prestamo.darMantenimiento();
		getView().refresh();
		addMessage("Se le ha dado un nuevo mantenimiento a este equipo prestado", 
				prestamo.getProducto().getModelo().toString());
		hideAction = true;
	}

	@Override
	public String getActionToHide() {
		return hideAction ? "Prestamo.darMantenimiento" : null;
	}

}

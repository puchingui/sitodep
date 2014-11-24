package net.tonerdepot.sitodep.actions;

import net.tonerdepot.sitodep.modelo.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

public class DarMantenimientoDesdePrestamoAction extends ViewBaseAction {

	@Override
	public void execute() throws Exception {
		Prestamo prestamo = XPersistence.getManager().find(
				Prestamo.class, getView().getValue("conduce"));
		
		prestamo.darMantenimiento();
		getView().refresh();
		addMessage("Se le ha dado un nuevo mantenimiento a este equipo prestado", prestamo.getMantenimientos());
		
	}

}

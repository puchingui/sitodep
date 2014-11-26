package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Tab(properties="fecha, tecnico.nombre, prestamo.producto.serial, reporte")
@Views({
	@View(members="Datos {fecha, tecnico; reporte} Prestamo {prestamo}"),
	@View(name="NoProducto", members="fecha, tecnico; reporte")
})
public class Mantenimiento extends Identificable {

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@ManyToOne
	@DescriptionsList
	@NoCreate
	@NoModify
	private Empleado tecnico;
	
	@ManyToOne
	@ReferenceView("Simple")
	@NoCreate
	@NoModify
	private Prestamo prestamo;
	
	@Stereotype("MEMO")
	private String reporte;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Empleado getTecnico() {
		return tecnico;
	}

	public void setTecnico(Empleado tecnico) {
		this.tecnico = tecnico;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
}

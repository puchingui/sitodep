package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.*;
import org.openxava.annotations.*;

@Entity
@Tabs({
	@Tab(properties="serial, tipo.descripcion, marca.nombre, modelo, ubicacion",
			baseCondition="vendido = false"),
	@Tab(name="Archivo", 
			properties="serial, tipo.descripcion, marca.nombre, modelo, ubicacion",
			baseCondition="vendido = true")
})
@View(name="Simple", members="serial, tipo; marca, modelo")
public class Producto {

	@Id
	@Column(length=32)
	private String serial;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Tipo tipo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Marca marca;
	
	@Required
	@Column(length=32)
	private String modelo;
	
	@Enumerated(EnumType.STRING)
	private Ubicacion ubicacion;
	
	@Stereotype("MEMO")
	private String observaciones;
	
	@OneToMany(mappedBy="producto", cascade=CascadeType.REMOVE)
	@CollectionView("NoProducto")
	@ListAction("ManyToMany.new")
	@ListProperties("conduce, fecha, cliente.codigo, cliente.nombre, motivo.descripcion, recibido")
	private Collection<Prestamo> prestamos;
	
	@Hidden
	private boolean prestado;
	
	@Hidden
	private boolean vendido;
	
	public enum Ubicacion {
		Almacen1,
		Almacen2,
		Taller,
		Prestado
	}
	
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;			
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Collection<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Collection<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	@AssertTrue(message="Este producto esta prestado, no puede modificarse.")
	private boolean isProductoPrestado() {
		if(ubicacion != Ubicacion.Prestado && isPrestado()) {
			return false;
		}
		return true;
	}
}

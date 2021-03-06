package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Tab(properties="rnc, codigo, nombre, contacto, email, telefono")
@View(name="Simple", members="rnc, codigo, nombre")
public class Cliente {
	
	@Id
	@Column(length=13)
	private String rnc;
	
	@Column(length=4)
	@SearchKey
	private int codigo;
	
	@Required
	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String contacto;

	@Stereotype("EMAIL")
	private String email;
	
	@Stereotype("WEBURL")
	private String url;

	@Stereotype("TELEPHONE")
	private String telefono;
	
	@Embedded
	private Direccion direccion;
	
	@OneToMany(mappedBy="cliente")
	@ListProperties("codigo, fecha, estado.descripcion, prioridad, equipo.serial, equipo.modelo")
	private Collection<OrdenTrabajo> ordenes;
	
	@OneToMany(mappedBy="cliente")
	@CollectionView("NoCliente")
	@ListProperties("conduce, fecha, motivo.descripcion, producto.serial, producto.marca.nombre, producto.modelo, recibido")
	private Collection<Prestamo> prestamos;
	
	public String getRnc() {
		return rnc;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Collection<OrdenTrabajo> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Collection<OrdenTrabajo> ordenes) {
		this.ordenes = ordenes;
	}

	public Collection<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Collection<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
}

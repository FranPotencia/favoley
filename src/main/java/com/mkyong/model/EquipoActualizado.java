package com.mkyong.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EQUIPO_ACTUALIZADO")
public class EquipoActualizado {

	
	@Id
	@Column(name="ID")
	private Long id;
	@Column(name = "NOMBREEQUIPO")
	private String nombreEquipo;
	@Column(name = "LOCALIDAD")
	private String localidad;
	@Column(name = "PROVINCIA")
	private String provincia;
	@Column(name = "DIRECCION")
	private String direccion;
	@Column(name = "PABELLON")
	private String pabellon;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MOVIL")
	private String movil;
	@Column(name = "PRIMERENTRENADOR")
	private String primerEntrenador;
	@Column(name = "FECHA_ALTA")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fechaAlta;
	@Lob
	@Column(name = "photo", columnDefinition="BLOB")
	private byte[] photo;
	
	@Transient
	private String fechaAltaFormateada;
	
	//CascadeType.ALL hace el borrado/actualización/inserción de todo lo que dependa de equipo, en este caso los jugadores
	//orphanRemoval es para que si borro un jugador, no me borre equipo
	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JugadorActualizado> jugadores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPabellon() {
		return pabellon;
	}

	public void setPabellon(String pabellon) {
		this.pabellon = pabellon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getPrimerEntrenador() {
		return primerEntrenador;
	}

	public void setPrimerEntrenador(String primerEntrenador) {
		this.primerEntrenador = primerEntrenador;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaAltaFormateada() {
		return fechaAltaFormateada;
	}

	public void setFechaAltaFormateada(String fechaAltaFormateada) {
		this.fechaAltaFormateada = fechaAltaFormateada;
	}
    
	
	public List<JugadorActualizado> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorActualizado> jugadores) {
		this.jugadores = jugadores;
	}
	
	
	
}
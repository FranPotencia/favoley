package com.mkyong.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "ARBITRO_ACTUALIZADO")
public class ArbitroActualizado{


	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "APELLIDOS")
	private String apellidos;
	@Column(name = "DNI")
	private String dni;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "LOCALIDAD")
	private String localidad;
	@Column(name = "PROVINCIA")
	private String provincia;
	@Column(name = "MOVIL")
	private String movil;
	@Column(name = "FECHA_NACIMIENTO")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fechaNacimiento;
	@Column(name = "LICENCIA")
	private String licencia;
	@Column(name = "NUMERO_LICENCIA")
	private int numeroLicencia;
	@Column(name = "DELEGACION")
	private String delegacion;
	

	@Transient
	private String fechaNacimientoFormateada;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getLicencia() {
		return licencia;
	}


	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}


	public int getNumeroLicencia() {
		return numeroLicencia;
	}


	public void setNumeroLicencia(int numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}


	public String getDelegacion() {
		return delegacion;
	}


	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}


	public String getFechaNacimientoFormateada() {
		return fechaNacimientoFormateada;
	}


	public void setFechaNacimientoFormateada(String fechaNacimientoFormateada) {
		this.fechaNacimientoFormateada = fechaNacimientoFormateada;
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


	public String getMovil() {
		return movil;
	}


	public void setMovil(String movil) {
		this.movil = movil;
	}
	
	
}

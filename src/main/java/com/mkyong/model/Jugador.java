package com.mkyong.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JUGADOR")
public class Jugador implements Serializable {

	
	private static final long serialVersionUID = -5277233895249768800L; //EXPLICAR
	
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
	@Column(name = "FECHA_NACIMIENTO")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fechaNacimiento;

	@Transient
	private String fechaNacimientoFormateada;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaNacimientoFormateada() {
		return fechaNacimientoFormateada;
	}

	public void setFechaNacimientoFormateada(String fechaNacimientoFormateada) {
		this.fechaNacimientoFormateada = fechaNacimientoFormateada;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
}

package com.mkyong.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "JORNADA")
public class Jornada {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NUMERO_JORNADA")
	private Integer numeroJornada;
	
	@Column(name = "FECHA")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fecha;

	@Transient
	private String fechaJornadaFormateada;

	// CascadeType.ALL hace el borrado/actualización/inserción de todo lo que
	// dependa de equipo, en este caso los jugadores
	// orphanRemoval es para que si borro un jugador, no me borre equipo
	@OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Partido> partidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaJornadaFormateada() {
		return fechaJornadaFormateada;
	}

	public void setFechaJornadaFormateada(String fechaJornadaFormateada) {
		this.fechaJornadaFormateada = fechaJornadaFormateada;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	
}

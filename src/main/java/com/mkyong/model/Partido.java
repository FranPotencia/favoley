package com.mkyong.model;

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
@Table(name = "PARTIDO")
public class Partido {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "EQUIPO_LOCAL")
	private String equipoLocal;
	@Column(name = "EQUIPO_VISITANTE")
	private String equipoVisitante;

	@Column(name = "FECHA_PARTIDO")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fechaPartido;

	// Provisional
	@Column(name = "RESULTADO")
	private String resultado;
	// Provisional
	// @Column(name = "SET1")
	// private String set1;
	// //Provisional
	// @Column(name = "SET2")
	// private String set2;
	// //Provisional
	// @Column(name = "SET3")
	// private String set3;
	// //Provisional
	// @Column(name = "SET4")
	// private String set4;
	// //Provisional
	// @Column(name = "SET4")
	// private String set5;

	// @Column(name = "SETS_LOCAL")
	// private Long setsLocal;
	// @Column(name = "SETS_VISITANTE")
	// private Long setsVisitante;

	@Transient
	private String fechaPartidoFormateada;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jornada")
	private Jornada jornada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Date getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getFechaPartidoFormateada() {
		return fechaPartidoFormateada;
	}

	public void setFechaPartidoFormateada(String fechaPartidoFormateada) {
		this.fechaPartidoFormateada = fechaPartidoFormateada;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

}

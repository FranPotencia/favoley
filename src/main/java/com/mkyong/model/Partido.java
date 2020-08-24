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

	@Column(name = "NUMERO_PARTIDO")
	private Long numeroPartido;

	@Column(name = "EQUIPO_LOCAL")
	private String equipoLocal;

	@Column(name = "EQUIPO_VISITANTE")
	private String equipoVisitante;

	@Column(name = "FECHA_PARTIDO")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date fechaPartido;

	@Column(name = "RESULTADO")
	private String resultado;

	@Column(name = "SET1")
	private String set1;

	@Column(name = "SET2")
	private String set2;

	@Column(name = "SET3")
	private String set3;

	@Column(name = "SET4")
	private String set4;

	@Column(name = "SET5")
	private String set5;

	@Transient
	private String fechaPartidoFormateada;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jornada")
	private Jornada jornada;

	@Column(name = "ID_ARBITRO")
	private Long idArbitro;

	@Transient
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSet1() {
		return set1;
	}

	public void setSet1(String set1) {
		this.set1 = set1;
	}

	public String getSet2() {
		return set2;
	}

	public void setSet2(String set2) {
		this.set2 = set2;
	}

	public String getSet3() {
		return set3;
	}

	public void setSet3(String set3) {
		this.set3 = set3;
	}

	public String getSet4() {
		return set4;
	}

	public void setSet4(String set4) {
		this.set4 = set4;
	}

	public String getSet5() {
		return set5;
	}

	public void setSet5(String set5) {
		this.set5 = set5;
	}

	public Long getIdArbitro() {
		return idArbitro;
	}

	public void setIdArbitro(Long idArbitro) {
		this.idArbitro = idArbitro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroPartido() {
		return numeroPartido;
	}

	public void setNumeroPartido(Long numeroPartido) {
		this.numeroPartido = numeroPartido;
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

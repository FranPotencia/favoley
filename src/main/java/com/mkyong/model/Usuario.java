package com.mkyong.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8426393190703352041L;
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "TIPO")
	private String tipo; // EQUIPO, ARBITRO, ADMINISTRADOR
	@Column(name = "ACCESO") //
	private String idAcceso; // idEquipo, licencia o idArbitro, todo

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdAcceso() {
		return idAcceso;
	}

	public void setIdAcceso(String idAcceso) {
		this.idAcceso = idAcceso;
	}

}
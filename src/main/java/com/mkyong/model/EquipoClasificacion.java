package com.mkyong.model;

public class EquipoClasificacion {

	private String posicion;
	private String nombre;
	private String partidosJugados;
	private String partidosGanados;
	private String partidosPerdidos;
	private String tanteosFavor;
	private String tanteosContra;
	private Long puntos;

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(String partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public String getPartidosGanados() {
		return partidosGanados;
	}

	public void setPartidosGanados(String partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public String getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public void setPartidosPerdidos(String partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	public String getTanteosFavor() {
		return tanteosFavor;
	}

	public void setTanteosFavor(String tanteosFavor) {
		this.tanteosFavor = tanteosFavor;
	}

	public String getTanteosContra() {
		return tanteosContra;
	}

	public void setTanteosContra(String tanteosContra) {
		this.tanteosContra = tanteosContra;
	}

	public Long getPuntos() {
		return puntos;
	}

	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}

}

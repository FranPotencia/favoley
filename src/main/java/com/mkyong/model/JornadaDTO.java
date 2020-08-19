package com.mkyong.model;

import java.util.List;

public class JornadaDTO {

	private Long idJornada;
	private String fechaJornada;
	private List<PartidoDTO> listaPartidoDTO;

	public Long getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Long idJornada) {
		this.idJornada = idJornada;
	}

	public String getFechaJornada() {
		return fechaJornada;
	}

	public void setFechaJornada(String fechaJornada) {
		this.fechaJornada = fechaJornada;
	}

	public List<PartidoDTO> getListaPartidoDTO() {
		return listaPartidoDTO;
	}

	public void setListaPartidoDTO(List<PartidoDTO> listaPartidoDTO) {
		this.listaPartidoDTO = listaPartidoDTO;
	}

}

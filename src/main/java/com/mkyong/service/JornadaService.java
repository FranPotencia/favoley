package com.mkyong.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jornada;
import com.mkyong.model.JornadaDTO;
import com.mkyong.model.Jugador;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.model.Partido;
import com.mkyong.repository.JornadaDAO;

@Service
public class JornadaService {

	@Autowired
	private JornadaDAO jornadaDAO;
	
	@Autowired
	private PartidoService partidoService;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


	public void deleteAll() {
		jornadaDAO.deleteAll();
		partidoService.deleteAll();
	}
	
	public List<Jornada> getAllJornadas() {

		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		List<Jornada> listaJornadas = new ArrayList<Jornada>();

		listaJornadas = jornadaDAO.findAll();

		if (!CollectionUtils.isEmpty(listaJornadas)) {
			for (Jornada jornada : listaJornadas) {
				if(jornada.getFecha()!=null) {
					String strDate = dateFormat.format(jornada.getFecha());
					jornada.setFechaJornadaFormateada(strDate);
				}
			}
		}

		return listaJornadas;
	}

	public Jornada getJornadaById(Long id) { // Devuelve jornada a través del ID

		Jornada jornada = jornadaDAO.findOne(id);

		return jornada;
	}

	public Jornada guardarJornada(Jornada jornada) {

		Jornada jornadaGuardada = jornadaDAO.save(jornada);
		if (!CollectionUtils.isEmpty(jornada.getPartidos())) {
			for (Partido partido : jornada.getPartidos()) {
				partido.setJornada(jornadaGuardada);
			}
			partidoService.guardarPartidos(jornada.getPartidos());
		}
		return jornadaGuardada;
	}

	public void guardarJornadaPartidos(JornadaDTO jornadaDTO) throws ParseException {
		Jornada jornada=jornadaDAO.findOne(jornadaDTO.getIdJornada());
		if(!StringUtils.isEmpty(jornadaDTO.getFechaJornada())) {
			jornada.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(jornadaDTO.getFechaJornada()));
		}
		jornadaDAO.save(jornada);
		partidoService.guardarPartidosArbitros(jornadaDTO);
	}
	
	public void deleteJornada(Long id) {
		jornadaDAO.delete(id);
	}

}

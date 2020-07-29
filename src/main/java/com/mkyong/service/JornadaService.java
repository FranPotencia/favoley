package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jornada;
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

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<Jornada> listaJornadas = new ArrayList<Jornada>();

		listaJornadas = jornadaDAO.findAll();

		if (!CollectionUtils.isEmpty(listaJornadas)) {
			for (Jornada jornada : listaJornadas) {
				// String strDate = dateFormat.format(jornada.getFecha());
				// jornada.setFechaAltaFormateada(strDate);
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

	public void deleteJornada(Long id) {
		jornadaDAO.delete(id);
	}

}

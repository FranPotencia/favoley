package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Arbitro;
import com.mkyong.model.ArbitroConfirmado;
import com.mkyong.model.Equipo;
import com.mkyong.model.Jornada;
import com.mkyong.model.Jugador;
import com.mkyong.model.Partido;
import com.mkyong.repository.ArbitroDAO;
import com.mkyong.repository.JornadaDAO;
import com.mkyong.repository.PartidoDAO;

@Service
public class PartidoService {

	@Autowired
	private PartidoDAO partidoDAO;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void guardarPartidos(List<Partido> partidos) {
		if (!CollectionUtils.isEmpty(partidos)) {
			Jornada jornada = partidos.get(0).getJornada();
			partidoDAO.deleteByJornada(jornada);
			partidoDAO.save(partidos);
		}

	}
	
	public void deleteAll() {
		partidoDAO.deleteAll();
	}
	
	//Me interesa hacer un GetAllPartidosByID ***** **** ****
	
	//Devuelve todos los partidos
	public List<Partido> getAllPartidos() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<Partido> listaPartidos = new ArrayList<Partido>();

		listaPartidos = partidoDAO.findAll();

		if (!CollectionUtils.isEmpty(listaPartidos)) {
			for (Partido partido : listaPartidos) {
				// String strDate = dateFormat.format(equipo.getFechaAlta());
				// equipo.setFechaAltaFormateada(strDate);
			}
		}
		
		return listaPartidos;
	}
	
	
	public Partido getPartidoById(Long id) { //Devuelve partido a través del ID
		
		Partido partido=partidoDAO.findOne(id);
		
		return partido;
	}
	
	public void deleteJornada(Long id) {
		partidoDAO.delete(id);
	}

}

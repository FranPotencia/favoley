package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Equipo;
import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jugador;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.repository.EquipoConfirmadoDAO;

@Service
public class EquipoConfirmadoService {

	@Autowired
	private EquipoConfirmadoDAO equipoConfirmadoDAO;

	@Autowired // porque estoy llamando a un componente de la aplicación
	private JugadorConfirmadoService jugadorConfirmadoService;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   public EquipoConfirmado getEquipoById(Long id) { //Devuelve equipo a través del ID
		
		EquipoConfirmado equipo=equipoConfirmadoDAO.findOne(id);
		
		if(!CollectionUtils.isEmpty(equipo.getJugadores())) { //(explicar collectionUtils) - Si el equipo contiene jugadores, formateo su fecha de nacimiento
			for (JugadorConfirmado jugador : equipo.getJugadores()) {
				jugador.setFechaNacimientoFormateada(dateFormat.format(jugador.getFechaNacimiento())); //Si no meto ninguna fecha, esta línea devuelve un error
			}
		}
		
		return equipo;
	}
	
	
	public List<EquipoConfirmado> getAllEquipos() {

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<EquipoConfirmado> listaEquipos = new ArrayList<EquipoConfirmado>();

		listaEquipos = equipoConfirmadoDAO.findAll();

		if (!CollectionUtils.isEmpty(listaEquipos)) {
			for (EquipoConfirmado equipoConfirmado : listaEquipos) {
				// String strDate = dateFormat.format(equipo.getFechaAlta());
				// equipo.setFechaAltaFormateada(strDate);
			}
		}

		return listaEquipos;
	}

	public EquipoConfirmado guardarEquipo(EquipoConfirmado equipo) {

		if (equipo.getId() != null) { // si el equipo ya ha sido guardado anteriormente (actualizar)
			if (!CollectionUtils.isEmpty(equipo.getJugadores())) { // Si el equipo tiene jugadores, asocio el equipo a
																	// cada jugador
				for (JugadorConfirmado jugador : equipo.getJugadores()) {
					jugador.setEquipo(equipo);
				}
				jugadorConfirmadoService.guardarJugadoresEquipoExistente(equipo.getJugadores());
				equipo=equipoConfirmadoDAO.save(equipo);
			}
			return equipo;
		} else { // si el equipo no ha sido guardado previamente
			EquipoConfirmado equipoGuardado = equipoConfirmadoDAO.save(equipo);
			if (!CollectionUtils.isEmpty(equipo.getJugadores())) {
				for (JugadorConfirmado jugador : equipo.getJugadores()) {
					jugador.setEquipo(equipoGuardado);
				}
				jugadorConfirmadoService.guardarJugadores(equipo.getJugadores());
			}
			return equipoGuardado;
		}
	}

	public void deleteEquipo(Long id) {
		equipoConfirmadoDAO.delete(id);
	}

}

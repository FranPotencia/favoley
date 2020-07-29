package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.EquipoActualizado;
import com.mkyong.model.JugadorActualizado;
import com.mkyong.repository.EquipoActualizadoDAO;

@Service
public class EquipoActualizadoService {

	@Autowired
	private EquipoActualizadoDAO equipoActualizadoDAO;

	@Autowired // porque estoy llamando a un componente de la aplicación
	private JugadorActualizadoService jugadorActualizadoService;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   public EquipoActualizado getEquipoById(Long id) { //Devuelve equipo a través del ID
		
		EquipoActualizado equipoActualizado=equipoActualizadoDAO.findOne(id);
		
		if(!CollectionUtils.isEmpty(equipoActualizado.getJugadores())) { //(explicar collectionUtils) - Si el equipo contiene jugadores, formateo su fecha de nacimiento
			for (JugadorActualizado jugadorActualizado : equipoActualizado.getJugadores()) {
				jugadorActualizado.setFechaNacimientoFormateada(dateFormat.format(jugadorActualizado.getFechaNacimiento())); //Si no meto ninguna fecha, esta línea devuelve un error
			}
		}
		
		return equipoActualizado;
	}
	
   public List<EquipoActualizado> getAllEquipos() {
		return equipoActualizadoDAO.findAll();
	}
   
//	public List<EquipoActualizado> getAllEquipos() {
//
//		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//		List<EquipoActualizado> listaEquipos = new ArrayList<EquipoActualizado>();
//
//		listaEquipos = equipoActualizadoDAO.findAll();
//
//		if (!CollectionUtils.isEmpty(listaEquipos)) {
//			for (EquipoActualizado equipoActualizado : listaEquipos) {
//				// String strDate = dateFormat.format(equipo.getFechaAlta());
//				// equipo.setFechaAltaFormateada(strDate);
//			}
//		}
//
//		return listaEquipos;
//	}

	public void guardarEquipo(EquipoActualizado equipoActualizado) {

		EquipoActualizado equipoGuardado = equipoActualizadoDAO.save(equipoActualizado);
		if (!CollectionUtils.isEmpty(equipoGuardado.getJugadores())) {
			for (JugadorActualizado jugador : equipoGuardado.getJugadores()) {
				jugador.setEquipo(equipoGuardado);
			}
			jugadorActualizadoService.guardarJugadores(equipoGuardado.getJugadores());
		}
	}

	public void deleteEquipo(Long id) {
		equipoActualizadoDAO.delete(id);
	}

}

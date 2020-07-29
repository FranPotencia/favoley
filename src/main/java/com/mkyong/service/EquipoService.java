package com.mkyong.service;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Equipo;
import com.mkyong.model.Jugador;
import com.mkyong.repository.EquipoDAO;

@Service
public class EquipoService {

	@Autowired
	private EquipoDAO equipoDAO;
	
	@Autowired
	private JugadorService jugadorService;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Equipo getEquipoById(Long id) { //Devuelve equipo a través del ID
		
		Equipo equipo=equipoDAO.findOne(id);
		
		if(!CollectionUtils.isEmpty(equipo.getJugadores())) { //(explicar collectionUtils) - Si el equipo contiene jugadores, formateo su fecha de nacimiento
			for (Jugador jugador : equipo.getJugadores()) {
				jugador.setFechaNacimientoFormateada(dateFormat.format(jugador.getFechaNacimiento())); //Si no meto ninguna fecha, esta línea devuelve un error
			}
		}
		
		return equipo;
	}
	
	
	
	
	/*
	public List<Equipo> getAllEquipos(){
		
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		 
		
		List<Equipo> listaEquipos=new ArrayList<Equipo>();
		
		listaEquipos=equipoDAO.findAll();
		
		if(!CollectionUtils.isEmpty(listaEquipos)) {
			for (Equipo equipo : listaEquipos) {
				//String strDate = dateFormat.format(equipo.getFechaAlta()); 
				//equipo.setFechaAltaFormateada(strDate);
			}
		}
		
		return listaEquipos;
	}
	*/
	
	public List<Equipo> getAllEquipos() {
		return equipoDAO.findAll();
	}
	
	
	public void guardarEquipo(Equipo equipo) {
		
		if(equipo.getId()!=null) {  //si el equipo ya ha sido guardado anteriormente (actualizar)
			if(!CollectionUtils.isEmpty(equipo.getJugadores())) { //Si el equipo tiene jugadores, asocio el equipo a cada jugador
				for (Jugador jugador : equipo.getJugadores()) {
					jugador.setEquipo(equipo);
				}
				jugadorService.guardarJugadores(equipo.getJugadores());
				equipoDAO.save(equipo);
			}
		} else { //si el equipo no ha sido guardado previamente
//			try {
//				byte[] photoDecoded = Base64.getDecoder().decode(equipo.getPhotoString().split(",")[1]);
//				equipo.setPhoto(photoDecoded);
//			} catch (Exception e) {
//				System.out.println("Ha ocurrido un error al convertir la foto");
//			}
			Equipo equipoGuardado=equipoDAO.save(equipo);
			if(!CollectionUtils.isEmpty(equipo.getJugadores())) {
				for (Jugador jugador : equipo.getJugadores()) {
					jugador.setEquipo(equipoGuardado);
				}
				jugadorService.guardarJugadores(equipo.getJugadores());
			}
		}
		
		
	}
	
	
	public void deleteEquipo(Long id) {
		equipoDAO.delete(id);
	}
}

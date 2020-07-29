package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.model.JugadorActualizado;
import com.mkyong.model.JugadorConfirmado;
import com.mkyong.repository.JugadorActualizadoDAO;
import com.mkyong.repository.JugadorConfirmadoDAO;

@Service
public class JugadorActualizadoService {

	@Autowired
	private JugadorActualizadoDAO jugadorActualizadoDAO;
	
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	/*
	public List<Jugador> getAllJugadores(){ 

		List<Jugador> listaJugadores=new ArrayList<Jugador>();
		
		listaJugadores=jugadorDAO.findAll();
		
		if(!CollectionUtils.isEmpty(listaJugadores)) {
			for (Jugador jugador : listaJugadores) {
				String strDate = dateFormat.format(jugador.getFechaNacimiento()); 
				jugador.setFechaNacimientoFormateada(strDate);
			}
		}
		
		return listaJugadores;
	}
	
	*/
	
	/*
	public Jugador guardarJugador(Jugador jugador) {
		jugador=jugadorDAO.save(jugador);
		String strDate = dateFormat.format(jugador.getFechaNacimiento()); 
		jugador.setFechaNacimientoFormateada(strDate);
		return jugadorDAO.save(jugador);
	}
	*/
	
	public void guardarJugadores(List<JugadorActualizado> jugadores) {
		jugadorActualizadoDAO.save(jugadores);
	}
	
	public void deleteJugador(Long id) {
		jugadorActualizadoDAO.delete(id);
	}
	
}

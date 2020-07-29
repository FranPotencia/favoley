package com.mkyong.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mkyong.model.Jugador;
import com.mkyong.repository.JugadorDAO;

@Service
public class JugadorService {

	@Autowired
	private JugadorDAO jugadorDAO;
	
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
	
	public void guardarJugadores(List<Jugador> jugadores) {
		jugadorDAO.save(jugadores);
	}
	
	public void deleteJugador(Long id) {
		jugadorDAO.delete(id);
	}
	
}

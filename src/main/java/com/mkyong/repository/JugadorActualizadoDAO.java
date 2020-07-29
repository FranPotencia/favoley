package com.mkyong.repository;

import org.springframework.stereotype.Repository;

import com.mkyong.model.Jugador;
import com.mkyong.model.JugadorActualizado;
import com.mkyong.model.JugadorConfirmado;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JugadorActualizadoDAO extends JpaRepository<JugadorActualizado, Long> {// <nombre tabla, id>  Esto es la api de persistencia de java para la tabla EQUIPO
	
}
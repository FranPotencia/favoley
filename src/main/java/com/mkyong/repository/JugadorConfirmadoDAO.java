package com.mkyong.repository;

import org.springframework.stereotype.Repository;

import com.mkyong.model.EquipoConfirmado;
import com.mkyong.model.Jugador;
import com.mkyong.model.JugadorConfirmado;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JugadorConfirmadoDAO extends JpaRepository<JugadorConfirmado, Long> {// <nombre tabla, id>  Esto es la api de persistencia de java para la tabla EQUIPO
	
	void deleteByEquipo(EquipoConfirmado equipo);
}
package com.mkyong.repository;

import org.springframework.stereotype.Repository;

import com.mkyong.model.Jugador;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JugadorDAO extends JpaRepository<Jugador, Long> {// <nombre tabla, id>  Esto es la api de persistencia de java para la tabla EQUIPO
	
}
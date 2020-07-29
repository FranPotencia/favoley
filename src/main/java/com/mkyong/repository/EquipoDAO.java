package com.mkyong.repository;

import org.springframework.stereotype.Repository;

import com.mkyong.model.Equipo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EquipoDAO extends JpaRepository<Equipo, Long> { // Esto es la api de persistencia de java para la tabla EQUIPO
	
}

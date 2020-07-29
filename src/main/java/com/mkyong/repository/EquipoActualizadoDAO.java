package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.EquipoActualizado;
import com.mkyong.model.EquipoConfirmado;

	@Repository
	public interface EquipoActualizadoDAO extends JpaRepository<EquipoActualizado, Long> { // Esto es la api de persistencia de java para la tabla EQUIPO
		
	}
	


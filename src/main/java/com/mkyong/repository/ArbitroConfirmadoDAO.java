package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.ArbitroConfirmado;

@Repository
public interface ArbitroConfirmadoDAO extends JpaRepository<ArbitroConfirmado, Long> { // Esto es la api de persistencia de java para la tabla EQUIPO
	
}

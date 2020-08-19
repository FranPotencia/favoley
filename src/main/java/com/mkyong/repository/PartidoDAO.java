package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.Jornada;
import com.mkyong.model.Partido;


@Repository
public interface PartidoDAO extends JpaRepository<Partido, Long> {

	void deleteByJornada(Jornada jornada);
	
	Partido findByNumeroPartido(Long numeroPartido);
}

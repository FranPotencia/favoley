package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.Jornada;

@Repository
public interface JornadaDAO extends JpaRepository<Jornada, Long> {

}

package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.Arbitro;

@Repository
public interface ArbitroDAO extends JpaRepository<Arbitro, Long> {

}

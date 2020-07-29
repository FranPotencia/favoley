package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
	
}

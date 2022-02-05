package com.generation.blogpessoal.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.BlogPessoal.model.UsuariosModel;

@Repository

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
	
	public List<UsuariosModel> findAllByNomeContainingIgnoreCase (String Nome);

	public Optional<UsuariosModel> findByUsuario(String usuario);
	
}